# Naive Mind Specialize Language


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
3. support lambda
4. add io functions, `open`,`readln`,`write`
5. load `lib.sl` when running.
6. add some Higher-order functions `forEach`,`map`,`from` in `lib.sl`
7. add some interesting grammar

## Example
```
fn main() {
    n = "\n";
    fh_r = open("http://www.baidu.com","<");
    fh_w = open("file:index.html",">+");
    fh_r from {line: return line; } map {l: return l+n; } forEach {l: write(fh_w,l); };
    close(fh_r);
    close(fh_w);
}
```