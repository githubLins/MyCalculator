package com.example.administrator.mycalculator;

public class MainActivity1 {
    NumStack number;
    CharList mychar;
    public MainActivity1(){
        number = new NumStack();
        mychar = new CharList();
    }
    public String toBehind(String express){                                   //变成后缀式
        String behind=new String();
        mychar.push('#');
        int a=0;
        int b=0;
        for(int i=0; i<express.length(); i++){
            if(express.charAt(i)=='('){
                a++;
            }
            if(express.charAt(i)==')'){
                if(b==0 && a==0){
                    System.out.println("输入有误！");
                    System.exit(0);
                }
                b++;
                if(express.charAt(i+1)=='('){
                    System.out.println("输入有误！");
                    System.exit(0);
                }
            }
            if(express.charAt(i)=='+'||express.charAt(i)=='*'||express.charAt(i)=='/'||express.charAt(i)=='%'){
                if((express.charAt(i-1)>='a'&&express.charAt(i-1)<='z')||(express.charAt(i-1)>='A'&&express.charAt(i-1)<='Z')){
                    if(express.charAt(i+1)<=57 && express.charAt(i+1)>=48||express.charAt(i+1)=='('|| express.charAt(i+1)==')'){
                        continue;
                    }
                    else{
                        System.out.println("输入有误！");
                        System.exit(0);
                    }
                }
                if((express.charAt(i+1)>='a'&&express.charAt(i+1)<='z')||(express.charAt(i+1)>='A'&&express.charAt(i+1)<='Z')){
                    if(express.charAt(i-1)<=57 && express.charAt(i-1)>=48||express.charAt(i-1)=='('|| express.charAt(i-1)==')'){
                        continue;
                    }
                    else{
                        System.out.println("输入有误！");
                        System.exit(0);
                    }
                }
                if(express.charAt(i-1)>57 || express.charAt(i-1)<48){
                    if(express.charAt(i-1)!='('&& express.charAt(i-1)!=')'){
                        System.out.println("输入有误！");
                        System.exit(0);
                    }
                }
                if(express.charAt(i+1)>57 || express.charAt(i+1)<48){
                    if(express.charAt(i+1)!='('&& express.charAt(i+1)!=')'){
                        System.out.println("输入有误！");
                        System.exit(0);
                    }
                }
            }
        }
        if(a!=b){
            System.out.println("输入有误！");
            System.exit(0);
        }
        for(int i=0; i<express.length(); i++){
            if( (express.charAt(i)<=57 && express.charAt(i)>=48) || express.charAt(i) == '.'){   //如果此字符是数字或小数点,则输出到后缀式
                behind += express.charAt(i);
            }
            else if(express.charAt(i) == '#'){                   								//如果是表达式结束符
                while(mychar.top != 1){                          								//从栈顶依次弹出所有运算符，并加到后缀式
                    behind += (";" + mychar.pop());
                }                                               								//循环完成后，已经得到想要的后缀式了
            }
            else if((express.charAt(i)>='a'&&express.charAt(i)<='z')||(express.charAt(i)>='A'&&express.charAt(i)<='Z'))
            {
                continue;
            }
            else{                                            									//如果是操作符，则用分号隔开，以便把几个表示同一个数字的字符变为double型
                behind += ";";

                if( Osp(express.charAt(i)) > Isp(mychar.top()) ){         						//如果比栈顶字符优先级高则入栈
                    mychar.push(express.charAt(i));
                }

                else if( Osp(express.charAt(i)) == Isp(mychar.top()) ){        					//如果和栈顶操作符优先级相等，则是右括号与左括号匹配的情况，应把左括号扔掉
                    mychar.pop();
                }
                else{                                                       					//如果比栈顶字符优先级低则弹出栈顶元素加入后缀式中，直至栈顶元素优先级比其低时就入栈
                    boolean higher = false;

                    while (!higher){
                        behind += (mychar.pop() + ";");

                        if( Osp(express.charAt(i)) > Isp(mychar.top())){
                            higher = true;
                            mychar.push(express.charAt(i));
                        }
                        else if( Osp(express.charAt(i)) == Isp(mychar.top()) ){        			//如果和栈顶操作符优先级相等，则是右括号与左括号匹配的情况，应把左括号扔掉
                            mychar.pop();
                            higher = true;
                        }
                    }
                }
            }
        }
        return behind;
    }
    public double caculate(String behind){                                  //计算后缀式
        double result = 0;
        String[] str = behind.split(";");                                   //将得到的后缀式按分号分隔开，形成一个字符串数组，然后逐一判断是否操作符和操作数
        for(int i = 0; i < str.length; i++){
            if(str[i].equals("+")){                                       //加法运算，将栈顶两个相加
                double b = number.pop();
                double a = number.pop();
                double num = a + b;
                number.push(num);
            }
            else if( str[i].equals("-") ){
                double b = number.pop();
                double a = number.pop();
                double num = a - b;
                number.push(num);
            }
            else if( str[i].equals("*") ){
                double b = number.pop();
                double a = number.pop();
                double num = a * b;
                number.push(num);
            }
            else if( str[i].equals("/") ){
                double b = number.pop();
                double a = number.pop();
                double num = a / b;
                number.push(num);
            }
            else if( str[i].equals("") ){                                 //字符数组中出现空字符串，如果是空字符串则跳过
                continue;
            }
            else{                                                          //如果是数字字符串，则转换为double型后存入栈
                double num = Double.parseDouble(str[i]);
                number.push(num);
            }
        }
        result = number.top();                                             //得出结果
        return result;
    }
    int Isp(char ch){                                          //传入栈内操作符，返回优先级，当返回-1时说明式子包含非法字符
        switch(ch){
            case '#'  :  return 0;
            case '('  :  return 1;
            case '+'  :  return 3;
            case '-'  :  return 3;
            case '*'  :  return 5;
            case '/'  :  return 5;
           // case '%'  :  return 5;
            case '^'  :  return 7;
            case ')'  :  return 8;
        }
        return -1;
    }
    int Osp(char ch){                                          //传入栈外操作符，返回栈外运算符的优先级，当返回-1时说明式子包含非法字符，应提示用户
        switch(ch){
            case '#'  :  return 0;
            case '('  :  return 8;
            case '+'  :  return 2;
            case '-'  :  return 2;
            case '*'  :  return 4;
            case '/'  :  return 4;
           // case '%'  :  return 4;
            case '^'  :  return 6;
            case ')'  :  return 1;
        }
        return -1;
    }
}
class NumStack{
    double[] num;                                                 		 //double数组存放操作数
    int top;                                                             //栈顶
    final int maxlen;                                                   //最长
    public NumStack(){                                                  //初始化字符栈
        maxlen = 200;
        num = new double[maxlen];
        top = 0;
    }
    public double pop(){                                               //把当前栈顶丢弃，并返回它的值
        if(top == 0){
            System.out.println("当前栈为空");
            //return -1;
            System.exit(0);
        }
        double i = num[top-1];
        top--;
        return i;
    }
    public void push(double n){                                        //把参数字符n放到栈顶
        if(top >= maxlen){
            System.out.println("栈已满");
            System.exit(0);
        }
        num[top] = n;
        top++;
    }
    public double top(){                                               //返回栈顶元素，但不丢弃，注意与pop()的区别
        if(top == 0){
            System.out.println("当前栈为空");
            System.exit(0);
        }
        return num[top-1];
    }
}
class CharList{
    char[] num;                                              //字符数组，用来存放操作符
    public int top;                                          //栈顶
    final int maxlen;                                        //最长
    public CharList(){                                       //初始化字符栈
        maxlen = 200;
        num = new char[maxlen];
        top = 0;
    }
    public char pop(){                                      //把当前栈顶丢弃，并返回它的值
        if(top == 0){
            System.out.println("当前栈为空");
            System.exit(0);
        }
        char i = num[top-1];
        top--;
        return i;
    }
    public void push(char n){                                //把参数字符n放到栈顶
        if(top >= maxlen){
            System.out.println("栈已满");
            System.exit(0);
        }
        num[top] = n;
        top++;
    }
    public char top(){                                       //返回栈顶元素，但不丢弃，注意与pop()的区别
        if(top == 0){
            System.out.println("当前栈为空");
            System.exit(0);
        }
        return num[top-1];
    }
}

