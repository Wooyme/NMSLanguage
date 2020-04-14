# 娜美（Native mind specialize language）

娜美是一门**运行在JVM(Java虚拟机)上的脚本语言**。  
近年来以JVM和LLVM为后端的语言不断涌现，其中JVM因为其成熟的GC，虚拟机优化技术和庞大的生态得到大量开发者的青睐，目前JVM已经拥有了Java、Scala、Kotlin、Groovy和Clojure等被广泛使用的语言。

# 语言特性

## 语法

### 类型机制
娜美本身采用隐式弱类型。为了使用Java生态，娜美提供了`to`函数作为显式类型转换函数。

### lambda
娜美的lambda语法如下：
```
fn main(){
    x = 2;
    v = [10,100,1000];
    v forEach {v: print(v/2+"\n"); }; //forEach是内置函数
}
```
娜美内置了如：`forEach`，`map`，`filter`这样的高阶函数。
同时为了保证无副作用，lambda中可以访问外部变量但是只能在lambda作用域内修改，即lambda内对外部变量的修改只在lambda内生效。  

### 函数式

考虑到人的思维方式是流程化的，这与传统函数的表达方式不同
> 人: 东西->操作->结果  
> 程序语言: 函数->参数->结果

所以娜美加入了一个新的语法特性，可以将
```
func3(func2(func1(a)))
```
改写为
```
a func1 func2 func3
```
这也是函数式编程的一种体现。

### 协程
*娜美的协程仍在开发阶段*
现代的高并发和异步构架的流行让协程成为了这几年的流行词。
```
client.query("SELECT deviceId FROM sstalink_device LIMIT "+offset+","+limit,{handle:@result} Handler);
await @result->(this,ar);
ar.result().getRows() forEach { v:
                v.deviceId println;
                ret.result = ret.result+v.deviceId+"\n";
 };
```
这是娜美协程的原始形态，未来可能会有较大修改。

## 生态

娜美运行在JVM上，可以继承Java的全部生态，通过`import`关键字可以导入外部包。由于Java是面向对象的语言，为了兼容Java，娜美中的Object可以显式声明实现Java中的接口。这样就可以实现Java和娜美的双向交互。
