package com.example.jungsunwoung.service.calcualation;


    class Calc {
        String  postfix(String infixExp) {
            Double value;
            boolean endOfNumber = false;
            String postfixExp = new String();
            ArrayStack stk = new ArrayStack();

            for(int i = 0; i < infixExp.length(); i++)
            {
                switch(infixExp.charAt(i))
                {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case '.':
                        postfixExp = postfixExp.concat(infixExp.charAt(i)+"");
                        endOfNumber = true;
                        break;
                    case '(':
                        if(endOfNumber == true)	  {
                            postfixExp = postfixExp.concat(" ");
                            endOfNumber = false;
                        }

                        stk.push(new Character('('));
                        break;
                    case ')':
                        if(endOfNumber == true)	  {
                            postfixExp = postfixExp.concat(" ");
                            endOfNumber = false;
                        }
                        while(((Character)stk.peek()).charValue() != '(' )
                            postfixExp = postfixExp.concat(((Character)stk.pop()).toString());
                        Object openParen = stk.pop();
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        if(endOfNumber == true)  {
                            postfixExp = postfixExp.concat(" ");
                            endOfNumber = false;
                        }
                        while ( !stk.isEmpty() && ((Character)stk.peek()).charValue() != '('
                                && 	getPrec(infixExp.charAt(i)) <= getPrec(((Character)stk.peek()).charValue()) )  {
                            postfixExp = postfixExp.concat(((Character)stk.pop()).toString());
                        }
                        stk.push(new Character(infixExp.charAt(i)));
                        break;
                }
            }

            if(endOfNumber == true) {
                postfixExp = postfixExp.concat(" ");
                endOfNumber = false;
            }

            //모든 작업이 끝나면 스택에 있는 연산자들을 모두 팝하여 출력한다.
            while( !stk.isEmpty())	{
                postfixExp = postfixExp.concat(((Character)stk.pop()).toString());
            }

            System.out.println(postfixExp);

            return postfixExp;
        }

        Double result(String postfixExp) {
            System.out.println(postfixExp);
            Double value, buffer;
            String temp = new String();
            ArrayStack stk = new ArrayStack();

            for(int i=0; i<postfixExp.length(); i++)    {
                switch(postfixExp.charAt(i))    {

                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case '.':
                        //여기까지는 아직 공백을 만나지 않았으므로 수식의 끝이 아니다.
                        temp = temp.concat(postfixExp.charAt(i)+"");
                        break;
                    case ' ':
                        //공백을 만나서야 비로서 수식을 스택에 넣는다.
                        //공백을 만나기전에 수식이 여러개 있었다면 temp에 붙어서 저장되어 있다.
                        stk.push(new Double(temp));
                        temp = new String();
                        break;
                    case '+':
                        value = new Double(((Double)stk.pop()).doubleValue() + ((Double)stk.pop()).doubleValue());
                        stk.push(value);
                        break;
                    case '-':
                        buffer = new Double(((Double)stk.pop()).doubleValue());
                        value = new Double(((Double)stk.pop()).doubleValue() - buffer.doubleValue());
                        stk.push(value);
                        break;
                    case '*':
                        value = new Double(((Double)stk.pop()).doubleValue() * ((Double)stk.pop()).doubleValue());
                        stk.push(value);
                        break;
                    case '/':
                        buffer = new Double(((Double)stk.pop()).doubleValue());
                        value = new Double(Math.round(((Double)stk.pop()).doubleValue() / buffer.doubleValue()*100)/100.0);
                        stk.push(value);
                        break;
                }
            }
            return (Double)stk.peek();
        }

        int getPrec(char op) {
            int prec = 0;
            switch (op)   {
                case '+':
                case '-':
                    prec = 1;
                    break;
                case '*':
                case '/':
                    prec = 2;
                    break;
            }
            return prec;
        }

        static boolean bracketsBalance (String exp) {
            ArrayStack stk = new ArrayStack(exp.length() +1);
            for (int i = 0; i < exp.length(); i++) {
                //Scan across the expression
                char ch = exp.charAt(i);
                if (  ch== '[' || ch == '('  )  {
                    stk.push( new Character(ch));
                }
                else if(ch == ']' || ch == ')')  {
                    //empty means brackets unmatched
                    if (stk.isEmpty())   return false;
                    char charFromStack = ((Character)stk.pop()).charValue();
                    if (  ch == ']' && charFromStack != '['
                            ||  (ch == ')' && charFromStack != '(')  )
                        return false;
                } // end if
            } // end for loop
            return stk.isEmpty();  //empty means matched,  else unmatched
        }
    }

