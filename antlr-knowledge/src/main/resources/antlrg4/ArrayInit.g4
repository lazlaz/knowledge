grammar ArrayInit;

init : '{' value (',' value)* '}';
value : init
	  | INT
	  ;
//语法分析器的规则必须以小写字母开头，词法分析器的规则必须用大写字母开头
INT : [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;