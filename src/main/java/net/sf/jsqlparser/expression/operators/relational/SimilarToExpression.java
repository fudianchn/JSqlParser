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

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;

public class SimilarToExpression extends BinaryExpression implements SupportsOldOracleJoinSyntax {

    private boolean not = false;
    private String escape = null;

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean b) {
        not = b;
    }

    @Override
    public <T, S> T accept(ExpressionVisitor<T> expressionVisitor, S context) {
        return expressionVisitor.visit(this, context);
    }

    @Override
    public String getStringExpression() {
        return "SIMILAR TO";
    }

    @Override
    public String toString() {
        String retval = (oraclePriorPosition == ORACLE_PRIOR_START ? "PRIOR " : "")
                + getLeftExpression() + " " + (not ? "NOT " : "") + getStringExpression()
                + " " + getRightExpression();
        if (escape != null) {
            retval += " ESCAPE " + "'" + escape + "'";
        }

        return retval;
    }

    public String getEscape() {
        return escape;
    }

    public void setEscape(String escape) {
        this.escape = escape;
    }

    public SimilarToExpression withEscape(String escape) {
        this.setEscape(escape);
        return this;
    }

    public SimilarToExpression withNot(boolean not) {
        this.setNot(not);
        return this;
    }

    @Override
    public SimilarToExpression withLeftExpression(Expression arg0) {
        return (SimilarToExpression) super.withLeftExpression(arg0);
    }

    @Override
    public SimilarToExpression withRightExpression(Expression arg0) {
        return (SimilarToExpression) super.withRightExpression(arg0);
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
    public SimilarToExpression withOldOracleJoinSyntax(int oldOracleJoinSyntax) {
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

    public SimilarToExpression withOraclePriorPosition(int oraclePriorPosition) {
        setOraclePriorPosition(oraclePriorPosition);
        return this;
    }

}
