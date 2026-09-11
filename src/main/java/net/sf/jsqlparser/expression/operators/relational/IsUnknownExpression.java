/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2025 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.expression.operators.relational;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

public class IsUnknownExpression extends ASTNodeAccessImpl
        implements Expression, SupportsOldOracleJoinSyntax {

    private Expression leftExpression;
    private boolean isNot = false;

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpression(Expression expression) {
        leftExpression = expression;
    }

    public boolean isNot() {
        return isNot;
    }

    public void setNot(boolean isNot) {
        this.isNot = isNot;
    }

    @Override
    public <T, S> T accept(ExpressionVisitor<T> expressionVisitor, S context) {
        return expressionVisitor.visit(this, context);
    }

    @Override
    public String toString() {
        return (oraclePriorPosition == ORACLE_PRIOR_START ? "PRIOR " : "") + leftExpression + " IS"
                + (isNot ? " NOT" : "") + " UNKNOWN";
    }

    public IsUnknownExpression withLeftExpression(Expression leftExpression) {
        this.setLeftExpression(leftExpression);
        return this;
    }

    public IsUnknownExpression withNot(boolean isNot) {
        this.setNot(isNot);
        return this;
    }

    public <E extends Expression> E getLeftExpression(Class<E> type) {
        return type.cast(getLeftExpression());
    }

    private int oraclePriorPosition = NO_ORACLE_PRIOR;

    @Override
    public int getOldOracleJoinSyntax() {
        return NO_ORACLE_JOIN;
    }

    @Override
    public void setOldOracleJoinSyntax(int oldOracleJoinSyntax) {
        throw new IllegalArgumentException(
                "oracle join operator (+) is not supported on this condition");
    }

    @Override
    public IsUnknownExpression withOldOracleJoinSyntax(int oldOracleJoinSyntax) {
        setOldOracleJoinSyntax(oldOracleJoinSyntax);
        return this;
    }

    @Override
    public int getOraclePriorPosition() {
        return oraclePriorPosition;
    }

    @Override
    public void setOraclePriorPosition(int oraclePriorPosition) {
        this.oraclePriorPosition = oraclePriorPosition;
    }

    public IsUnknownExpression withOraclePriorPosition(int oraclePriorPosition) {
        setOraclePriorPosition(oraclePriorPosition);
        return this;
    }

}
