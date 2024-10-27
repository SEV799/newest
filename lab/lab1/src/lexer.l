%option noyywrap
%option nounput
%option noinput
%top{
    #include <stdarg.h>
    #include "common.h"
    #include <string>
    #include <cmath>
    using namespace std;
}
%{
    extern dump_type_t dump_type;

    inline void dump_tokens(const char* format, ...){
        va_list args;
        va_start(args, format);
        if (dump_type == TOKENS)
            vfprintf(yyout, format, args);
        va_end(args);
    }

    int column = 1;
    int last_token_was_number = 0; // 用于判断上一个 token 是否为数字
%}

/* definitions section */

DECIMAL ([-]?([1-9][0-9]*|0))
OCTAL ([-]?0[0-7]+)
HEXADECIMAL ([-]?0[xX][0-9A-Fa-f]+)
HEX_FLOAT ([-]?0[xX]([0-9A-Fa-f]*\.[0-9A-Fa-f]+|[0-9A-Fa-f]+)([pP][+-]?[0-9]+)?)
FLOAT ([-]?([0-9]*[.][0-9]+([eE][+-]?[0-9]+)?|[0-9]+[eE][+-]?[0-9]+))
ID [[:alpha:]_][_[:alpha:][:digit:]]*
EOL (\r\n|\n|\r)
WHITE [\t ]

%x BLOCKCOMMENT            
BLOCKCOMMENTBEGIN "/*"           
BLOCKCOMMENTELEMENT .|\n         
BLOCKCOMMENTEND "*/" 
LINECOMMENT \/\/[^\n]*  

%% 
{LINECOMMENT} {}   //处理注释，使用定义好的独占状态BLOCKCOMMENT
{BLOCKCOMMENTBEGIN} {BEGIN BLOCKCOMMENT;}
<BLOCKCOMMENT>{BLOCKCOMMENTELEMENT} {}
<BLOCKCOMMENT>{BLOCKCOMMENTEND} {BEGIN INITIAL;} //使用宏BEGIN来切换状态，初始状态默认为INITIAL


"int" {
    dump_tokens("INT\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("int");
    last_token_was_number = 0; 
}

"float" {
    dump_tokens("FLOAT\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("float");
    last_token_was_number = 0; 
}

"void" {
    dump_tokens("VOID\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("void");
    last_token_was_number = 0; 
}

"if" {
    dump_tokens("IF\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("if");
    last_token_was_number = 0; 
}

"else" {
    dump_tokens("ELSE\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("else");
    last_token_was_number = 0; 
}

"return" {
    dump_tokens("RETURN\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("return");
    last_token_was_number = 0; 
}

"const" {
    dump_tokens("CONST\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("const");
    last_token_was_number = 0; 
}

"while" {
    dump_tokens("WHILE\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("while");
    last_token_was_number = 0; 
}

"break" {
    dump_tokens("BREAK\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("break");
    last_token_was_number = 0; 
}

"continue" {
    dump_tokens("CONTINUE\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += strlen("continue");
    last_token_was_number = 0; 
}

"=" {
    dump_tokens("ASSIGN\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"==" {
    dump_tokens("EQUAL\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 2;
    last_token_was_number = 0; 
}

"!=" {
    dump_tokens("NOTEQUAL\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 2;
    last_token_was_number = 0; 
}

"<" {
    dump_tokens("LESS\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

">" {
    dump_tokens("GREATER\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"<=" {
    dump_tokens("LESSEQUAL\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 2;
    last_token_was_number = 0; 
}

">=" {
    dump_tokens("GREATEREQUAL\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 2;
    last_token_was_number = 0; 
}

"+" {
    dump_tokens("ADD\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"-" {
    dump_tokens("SUB\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"*" {
    dump_tokens("MUL\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"/" {
    dump_tokens("DIV\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"%" {
    dump_tokens("MOD\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"&&" {
    dump_tokens("AND\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 2;
    last_token_was_number = 0; 
}

"||" {
    dump_tokens("OR\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 2;
    last_token_was_number = 0; 
}

"!" {
    dump_tokens("NOT\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"," {
    dump_tokens("COMMA\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column+=1;
}

";" {
    dump_tokens("SEMICOLON\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column+=1;
}

"(" {
    dump_tokens("LBRACE\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

")" {
    dump_tokens("RBRACE\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"{" {
    dump_tokens("LBRACE\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

"}" {
    dump_tokens("RBRACE\t\t%s\t\t%d\t%d\n", yytext, yylineno, column);
    column += 1;
    last_token_was_number = 0; 
}

{DECIMAL} {
    long long decimal = atoll(yytext); // 转为长整型
    dump_tokens("DECIMAL\t\t%s\t\t%d\t%d\t%lld\n", yytext, yylineno, column, decimal);
    column += strlen(yytext);
    last_token_was_number = 1; 
}

{OCTAL} {
    int octal = strtol(yytext, NULL, 8);
    dump_tokens("OCTAL\t\t%s\t\t%d\t%d\t%d\n", yytext, yylineno, column, octal);
    column += strlen(yytext);
    last_token_was_number = 1; 
}

{HEXADECIMAL} {
    int hexadecimal = strtol(yytext, NULL, 16);
    dump_tokens("HEXADECIMAL\t%s\t\t%d\t%d\t%d\n", yytext, yylineno, column, hexadecimal);
    column += strlen(yytext);
    last_token_was_number = 1; 
}

{FLOAT} {
    float float_val = atof(yytext);
    dump_tokens("FLOAT\t\t%s\t\t%d\t%d\t%f\n", yytext, yylineno, column, float_val);
    column += strlen(yytext);
    last_token_was_number = 1; 
}

{HEX_FLOAT} {
    char* endptr;
    double hex_float_val = strtod(yytext, &endptr); // 先转化为十进制

    // 如果有指数部分，解析并计算
    if (*endptr == 'p' || *endptr == 'P') {
        int exponent = atoi(endptr + 1); // 获取指数
        hex_float_val *= pow(2, exponent); // 乘以2的指数
    }

    dump_tokens("HEX_FLOAT\t%s\t\t%d\t%d\t%f\n", yytext, yylineno, column, hex_float_val);
    column += strlen(yytext);
    last_token_was_number = 1; 
}


{ID} {
    dump_tokens("ID\t\t%s\t\t%d\t%d\t%p\n", yytext, yylineno, column,yytext);
    column+=strlen(yytext);
    last_token_was_number = 0;
}
 

{WHITE} column++;
{EOL} {
    yylineno++;
    column = 1;  
    last_token_was_number = 0; 
}


%% 
