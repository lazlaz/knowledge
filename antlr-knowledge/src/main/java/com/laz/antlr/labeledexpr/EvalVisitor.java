package com.laz.antlr.labeledexpr;

import java.util.HashMap;
import java.util.Map;

import com.laz.antlr.labeledexpr.LabeledExprParser.AddSubContext;
import com.laz.antlr.labeledexpr.LabeledExprParser.AssignContext;
import com.laz.antlr.labeledexpr.LabeledExprParser.IdContext;
import com.laz.antlr.labeledexpr.LabeledExprParser.IntContext;
import com.laz.antlr.labeledexpr.LabeledExprParser.MulDivContext;
import com.laz.antlr.labeledexpr.LabeledExprParser.ParensContext;
import com.laz.antlr.labeledexpr.LabeledExprParser.PrintExprContext;

public class EvalVisitor extends LabeledExprBaseVisitor<Integer>{
	Map<String,Integer> memory = new HashMap<String,Integer>();
	@Override
	public Integer visitAssign(AssignContext ctx) {
		String id = ctx.ID().getText();
		int value = visit(ctx.expr()); //计算id=expr后面的expr的值
		memory.put(id, value);
		return value;
	}
	
	@Override
	public Integer visitPrintExpr(PrintExprContext ctx) {
		Integer value = visit(ctx.expr());
		System.out.println(value);
		return 0;
	}
	
	@Override
	public Integer visitInt(IntContext ctx) {
		return Integer.valueOf(ctx.INT().getText());
	}
	
	@Override
	public Integer visitId(IdContext ctx) {
		String id = ctx.ID().getText();
		if (memory.containsKey(id)) {
			return memory.get(id);
		}
		return 0;
	}
	
	@Override
	public Integer visitMulDiv(MulDivContext ctx) {
		int left = visit(ctx.expr(0));
		int right = visit(ctx.expr(1));
		if (ctx.op.getType() == LabeledExprParser.MUL) 
			return left*right;
		return left/right;
	}
	
	@Override
	public Integer visitAddSub(AddSubContext ctx) {
		int left = visit(ctx.expr(0));
		int right = visit(ctx.expr(1));
		if (ctx.op.getType() == LabeledExprParser.ADD) 
			return left+right;
		return left-right;
	}
	
	@Override
	public Integer visitParens(ParensContext ctx) {
		return visit(ctx.expr());
	}
}
