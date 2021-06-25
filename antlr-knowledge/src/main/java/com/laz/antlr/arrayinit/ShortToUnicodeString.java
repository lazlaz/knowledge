package com.laz.antlr.arrayinit;

import com.laz.antlr.arrayinit.ArrayInitParser.InitContext;
import com.laz.antlr.arrayinit.ArrayInitParser.ValueContext;

public class ShortToUnicodeString extends ArrayInitBaseListener{
	@Override
	public void enterInit(InitContext ctx) {
		System.out.print('"');
	}
	
	@Override
	public void exitInit(InitContext ctx) {
		System.out.print('"');
	}
	
	@Override
	public void enterValue(ValueContext ctx) {
		int value = Integer.valueOf(ctx.INT().getText());
		System.out.printf("\\u%04x",value);
	}
	
}
