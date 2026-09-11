/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2023 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.expression.operators.relational;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

public class MemberOfExpression extends ASTNodeAccessImpl
        implements Expression, SupportsOldOracleJoinSyntax {

    Expression leftExpression;
    Expression rightExpression;
    boolean isNot;

    public MemberOfExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public MemberOfExpression setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
        return this;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public MemberOfExpression setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
        return this;
    }

    public boolean isNot() {
        return isNot;
    }

    public MemberOfExpression setNot(boolean not) {
        isNot = not;
        return this;
    }

    @Override
    public String toString() {
        return (oraclePriorPosition == ORACLE_PRIOR_START ? "PRIOR " : "") + leftExpression
                + " MEMBER OF " + rightExpression;
    }

    @Override
    public <T, S> T accept(ExpressionVisitor<T> expressionVisitor, S context) {
        return expressionVisitor.visit(this, context);
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
    public MemberOfExpression withOldOracleJoinSyntax(int oldOracleJoinSyntax) {
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

    public MemberOfExpression withOraclePriorPosition(int oraclePriorPosition) {
        setOraclePriorPosition(oraclePriorPosition);
        return this;
    }

}
