fn println(string){
    print(string);
    print("\n");
}

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

fn map(this,lambda){
    arr = members(this);
    if(arr==null){
        result = [];
        size = getSize(this);
        i = 0;
        while(i<size){
            result[] = lambda(this[i],i);
            i=i+1;
        }
        return result;
    }else{
        result = {};
        size = getSize(arr);
        i = 0;
        while(i<size){
            result[arr[i]] = lambda(this[arr[i]],arr[i]);
            i=i+1;
        }
        return result;
    }
}

fn from(this,lambda){
    line = readln(this);
    i = 0;
    result = new();
    while(!isNull(line)){
        result[i] = lambda(line);
        line = readln(this);
        i = i+1;
    }
    return result;
}