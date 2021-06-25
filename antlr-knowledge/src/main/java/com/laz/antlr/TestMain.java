package com.laz.antlr;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import com.laz.antlr.arrayinit.ArrayInitLexer;
import com.laz.antlr.arrayinit.ArrayInitParser;
import com.laz.antlr.arrayinit.ShortToUnicodeString;

public class TestMain {
	@Test
	public void test1() throws IOException {
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		ArrayInitLexer lexer = new ArrayInitLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ArrayInitParser parser = new ArrayInitParser(tokens);
		ParseTree tree = parser.init();
		System.out.println(tree.toStringTree(parser));
	}
	
	//翻译字符串
	@Test
	public void test2() throws IOException {
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		ArrayInitLexer lexer = new ArrayInitLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ArrayInitParser parser = new ArrayInitParser(tokens);
		ParseTree tree = parser.init();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new ShortToUnicodeString(), tree);
		System.out.println();
	}
}
