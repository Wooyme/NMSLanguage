fn main() {
    a = new();
    a.a = "Hello";
    a["b"] = "World";
    a[0] = "!";
    a forEach { v,k: println("Key:"+k+",Value:"+v); };
}
