/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.expression.operators.relational;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.ASTNodeAccessImpl;
import net.sf.jsqlparser.schema.Column;

public class IsNullExpression extends ASTNodeAccessImpl
        implements Expression, SupportsOldOracleJoinSyntax {

    private Expression leftExpression;
    private boolean not = false;
    private boolean useIsNull = false;
    private boolean useNotNull = false;

    public IsNullExpression() {}

    public IsNullExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public IsNullExpression(String columnName, boolean not) {
        this.leftExpression = new Column(columnName);
        this.not = not;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpression(Expression expression) {
        leftExpression = expression;
    }

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean b) {
        not = b;
    }

    public boolean isUseIsNull() {
        return useIsNull;
    }

    public void setUseIsNull(boolean useIsNull) {
        this.useIsNull = useIsNull;
    }

    public boolean isUseNotNull() {
        return useNotNull;
    }

    public IsNullExpression setUseNotNull(boolean useNotNull) {
        this.useNotNull = useNotNull;
        return this;
    }

    @Override
    public <T, S> T accept(ExpressionVisitor<T> expressionVisitor, S context) {
        return expressionVisitor.visit(this, context);
    }

    @Override
    public String toString() {
        String prior = oraclePriorPosition == ORACLE_PRIOR_START ? "PRIOR " : "";
        if (useNotNull) {
            return prior + leftExpression + " NOTNULL";
        } else if (useIsNull) {
            return prior + leftExpression + (not ? " NOT" : "") + " ISNULL";
        } else {
            return prior + leftExpression + " IS " + (not ? "NOT " : "") + "NULL";
        }
    }

    public IsNullExpression withUseIsNull(boolean useIsNull) {
        this.setUseIsNull(useIsNull);
        return this;
    }

    public IsNullExpression withLeftExpression(Expression leftExpression) {
        this.setLeftExpression(leftExpression);
        return this;
    }

    public IsNullExpression withNot(boolean not) {
        this.setNot(not);
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
    public IsNullExpression withOldOracleJoinSyntax(int oldOracleJoinSyntax) {
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

    public IsNullExpression withOraclePriorPosition(int oraclePriorPosition) {
        setOraclePriorPosition(oraclePriorPosition);
        return this;
    }

}
