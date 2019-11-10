fn main() {
     a = "Hello";
     b = new();
     b[0] = new();
     b[0][0] = "C";
     b[0][1] = "D";
     b forEach { x:
        x forEach {y: println(y+a); };
        x[0] = "E";
     };
     println(b[0][0]);
}
