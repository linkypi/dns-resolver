## 基于JNA调用实现DNS域名异步解析
### 1. before
虽然JNI也可以完成Java与c/c++的互通，但是在返回特定数据时会异常异常麻烦。如返回一个 Map<Integer,List<String>> 则需要先构建一个各种数据类型，操作如下：
``` java
jobject buildHashMap(JNIEnv* env, map<int, vector<string>> mapper) {
	jclass class_hashmap = env->FindClass("java/util/HashMap");
	jmethodID hashmap_construct_method = env->GetMethodID(class_hashmap, "<init>", "()V");
	printf("get hashmap_construct_method success \n");
	jobject obj_hashmap = env->NewObject(class_hashmap, hashmap_construct_method);
	jmethodID hashmap_put_method = env->GetMethodID(class_hashmap, "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
	printf("get hashmap_put_method success \n");
	map<int, vector<string>>::iterator iter;
	for (iter = mapper.begin(); iter != mapper.end(); iter++)
	{
		vector<string>& ips = iter->second;
		if (ips.size() == 0) {
			continue;
		}
		// new一个ArrayList对象
		jclass class_arraylist = env->FindClass("java/util/ArrayList");
		jmethodID arraylist_construct_method = env->GetMethodID(class_arraylist, "<init>", "()V");
		jobject obj_arraylist = env->NewObject(class_arraylist, arraylist_construct_method, "");
		jmethodID arraylist_add_method = env->GetMethodID(class_arraylist, "add", "(Ljava/lang/Object;)Z");
		printf("get arraylist_add_method success \n");
		for (auto item : ips)
		{
			env->CallBooleanMethod(obj_arraylist, arraylist_add_method, item);
		}

		env->CallObjectMethod(obj_hashmap, hashmap_put_method, iter->first, obj_arraylist);
	}
	return obj_hashmap;
}
```
几乎每种类型都需要先找到对应的Java类型，然后再找到构造函数，最后NewObject，异常麻烦。有了JNA就省去了该部分代码，可以在Java端使用 xxxxByReference 
来完成复杂数据类型的取值操作。

### 2. 重点 - 完成DNS异步解析功能
为什么需要用到DNS异步解析功能，这个起源于项目中使用到rocketmq-4.8.0时发现，当我们在配置文件中的brokerIP1设置的是域名时，会导致某条消息已消费但是实际上控制台的状态却消失未未消费。
最后查看源码发现是因为rocketmq没有对该属性进行域名解析，导致判断出错。后来我就在github提了一个 [issue](https://github.com/apache/rocketmq/issues/2697) ，后来发现竟然还可以使用异步的方式来解析域名，
所以才找到了三方高性能网络库 libevent 来解析。该库基于C语言开发，故需要用到 JNA . 
