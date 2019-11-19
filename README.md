# Native Mind Specialize Language

## NMSL


## From SimpleLanguage
> https://github.com/graalvm/simplelanguage

A simple demonstration language built using Truffle for the GraalVM.

SimpleLanguage is heavily documented to explain the how and why of writing a
Truffle language. A good way to find out more is to read the source with
comments. Start reading [here](https://github.com/graalvm/simplelanguage/blob/master/language/src/main/java/com/oracle/truffle/sl/SLLanguage.java). 
We also like to encourage people to clone the repository and start hacking.

This repository is licensed under the permissive UPL licence. Fork it to begin
your own Truffle language.

For instructions on how to get started please refer to [our website](http://www.graalvm.org/docs/graalvm-as-a-platform/implement-language/)

## Added Feature

1. Integer -> Decimal
2. add `\"`,`\n`,`\r`,`\t` to string.
3. add `\x` to string, like `\x0a`.
4. support lambda
5. add io functions, `open`,`readln`,`write`
6. load `lib.sl` when running.
7. add some Higher-order functions `forEach`,`map`,`from` in `lib.sl`
8. add some interesting grammar
9. add `[]` to create array
10. add `{}` to create object
11. add `import`,`load`
12. add `link`,`unlink`,`fr`

## load
```nmsl
// Test.nmsl
load "language/tests/LoadTest.nmsl";
fn main(){
    test();
}
```
```nmsl
// LoadTest.nmsl
fn test(){
    print("This is Test!");
}
```
加载其他源文件中的函数

## import
```nmsl
map = import "java.util.HashMap";
pq = import "java.util.PriorityQueue";
fn main() {
    a = map();
    a.put("Hello","你好");
    a.put("Alphabet",["A","B","C"]);
    a.put("Object",{a:"E",b:"F",c:"G"});
    print(a.get("Hello")[0]);
    print(a.remove("Hello")[1]+"\n");
    print(a.get("Alphabet")[0]+"\n");
    a.get("Object") forEach { v,k: print(k+":"+v+"\n"); };
    b = pq();
    b.add(10);
    b.add(5);
    b.add(7);
    print(b.poll()+"\n");
}
```
导入jdk与classpath中的类。

## link&unlink
```nmsl
pq = import "java.util.PriorityQueue";
fn main(){
    a = {value:1000};
    b = {value:100};
    a.compareTo = {this,other: return toInt(this.value-other.value); };
    b.compareTo = {this,other: return toInt(this.value-other.value); };
    a1 = link(a,"java.lang.Comparable");
    b1 = link(b,"java.lang.Comparable");
    q = pq();
    q.add(a1);
    q.add(b1);
    print(fr(q.poll()).value+"\n");
    print(fr(q.poll()).value+"\n");
    print(fr(q.poll()).value+"\n");
    unlink(a1);
    unlink(b1);
}
```
`link`绑定nmsl的Object与java中的interface.以`Comparable`为例，继承`Comparable`中需要实现`compareTo`方法,
只要Object中存在`compareTo`属性则link产生的`Java Object`会调用该属性。

`unlink`解绑,如果不解绑会造成内存泄露

`fr`从`Java Object`中还原`NMSL Object`

## lambda
```nmsl
fn main(){
    x = 2;
    v = [10,100,1000];
    v forEach {v: print(v/2+"\n"); };
}
//forEach 定义
fn forEach(this,lambda){
    arr = members(this);
    if(arr==null){
        size = getSize(this);
        i = 0;
        while(i<size){
            lambda(this[i],i);
            i=i+1;
        }
    }else{
        size = getSize(arr);
        i = 0;
        while(i<size){
            lambda(this[arr[i]],arr[i]);
            i=i+1;
        }
    }
}
```
> tips: 当函数只有两个参数，且第二个参数是函数时，可以写成 arg1 function {};

为了保证不产生副作用，lambda内只能访问外部变量，不能修改

> 用了复制context的方式，所以改了也只是局部改动而已

```nmsl
fn main(){
    x = 2;
    v = [10,100,1000];
    v forEach {v: print(v/2+"\n"); };
    y = {a:5};
    w = [5,4,3,2,1];
    w forEach { v:
        print(v/y.a + "\n");
        y.a = y.a - 1;
    };
}
```
context的复制不是深拷贝，所以y内部的属性可以修改

## Multiple Thead
```nmsl
Thread = import "java.lang.Thread";
fn main(){
    runnable = {};
    runnable.run = {this:
        Thread.sleep(500);
        print("Hello!");
    };
    r = link(runnable,"java.lang.Runnable");
    thread = Thread(r);
    thread.start();
    print("World!");
    sleep(1000);
    print("\n");
}
```
使用java的`Thread`

## Builtins

### open
```
// open(URL,Option);
a = open("http://www.baidu.com","<");
b = open("file:index.html",">+");
c = open("shell:ls","<");
```
URL参数支持 `http` `https` `file` `shell` 关键字
Option 包括 `<`,`>`,`<+`,`>+`,`>>+` `+`表示若文件不存在则创建，`>>`表示附加。
