// 创建对象的方法
var obj0 = new Object(); // 创建一个没有属性和属性值的对象
var obj1 = {'name':'steve','age':27}; // 创建一个带属性和属性值的对象

//创建对象再添加属性和属性值
var person = new Object();
person['name']='steve';
person['age']=27;

var age = person['age']
var name= person.name;




//基本数据类型本身不是对象,但是和java相似,也提供了装箱的功能
var str= new String('value');


//数组也是对象
var arr= ['one','two'];
var arr = ['0':'one','1':'two'];
// 以上二者是相等的

arr[0];// one
arr['0']; // one

// 函数也是对象


// 声明的方式,在运行之前就被加载到引擎中
function f1(a,b){
   return a+b;
}

// 表达式的方式,运行到定义行的时候才会进行加载
var f2 = function(a,b){
    return a+b;
}

//属于表达式
var foo2 = function foo3(a,b) {
     return a + b;
};

//定义对象的时候 add属性的值就是个函数
var  obj = {
    add: function(a,b){
         return a+b;
     }
};


// 可以将声明式的函数赋值给对象的属性
var obj=new Object();
obj.add1 = add;
function add (a,b){
	return a+b;
};



//JS 中没有类和接口的概念,但是有继承的概念,但是这种继承和java的有点区别,是指的对象间的一种关系
//指的是对象A可以直接用对象B中的属性元素

var b={prop_of_b:'property of b'};
var a ={__proto__:b};

a.prop_of_b;//property of b
//对象a 的原型就是对象b,也可以称为对象a继承了对象b.


// 对于原型中的属性名和对象自身的属性名冲突时候,优先读取的是自身的.

// 同样的,在a和b中都没有的属性,就会顺着原型链,一直找下去.

// 一直找下去的最后一站是对象Object.prototype,在这个对象中如没有需要的属性,则返回的值是null.


























