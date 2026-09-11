/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2026 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.expression.operators.relational;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.test.TestUtils;
import org.junit.jupiter.api.Test;

public class OraclePriorPositionTest {

    private static Expression whereOf(String sqlStr) throws JSQLParserException {
        PlainSelect select =
                (PlainSelect) TestUtils.assertSqlCanBeParsedAndDeparsed(sqlStr, true);
        return select.getWhere();
    }

    @Test
    public void testPriorBetween() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a BETWEEN 1 AND 2");
        Between between = (Between) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                between.getOraclePriorPosition());
    }

    @Test
    public void testPriorLike() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a LIKE 'x%'");
        LikeExpression like = (LikeExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                like.getOraclePriorPosition());
    }

    @Test
    public void testPriorNotLike() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a NOT LIKE 'x%'");
        LikeExpression like = (LikeExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                like.getOraclePriorPosition());
    }

    @Test
    public void testPriorSimilarTo() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a SIMILAR TO 'x'");
        LikeExpression similarTo = (LikeExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                similarTo.getOraclePriorPosition());
    }

    @Test
    public void testPriorSimilarToOnSeparateTokens() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a SIMILAR\nTO 'x'");
        SimilarToExpression similarTo = (SimilarToExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                similarTo.getOraclePriorPosition());
    }

    @Test
    public void testPriorIsNull() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a IS NULL");
        IsNullExpression isNull = (IsNullExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                isNull.getOraclePriorPosition());
    }

    @Test
    public void testPriorIsNotNull() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a IS NOT NULL");
        IsNullExpression isNull = (IsNullExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                isNull.getOraclePriorPosition());
    }

    @Test
    public void testPriorIsTrue() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a IS TRUE");
        IsBooleanExpression isBoolean = (IsBooleanExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                isBoolean.getOraclePriorPosition());
    }

    @Test
    public void testPriorIsUnknown() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a IS UNKNOWN");
        IsUnknownExpression isUnknown = (IsUnknownExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                isUnknown.getOraclePriorPosition());
    }

    @Test
    public void testPriorIsDistinctFrom() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a IS DISTINCT FROM b");
        IsDistinctExpression isDistinct = (IsDistinctExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                isDistinct.getOraclePriorPosition());
    }

    @Test
    public void testPriorMemberOf() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a MEMBER OF (1)");
        MemberOfExpression memberOf = (MemberOfExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                memberOf.getOraclePriorPosition());
    }

    @Test
    public void testPriorIn() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a IN (1, 2)");
        InExpression in = (InExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                in.getOraclePriorPosition());
    }

    @Test
    public void testPriorNotIn() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE PRIOR a NOT IN (1, 2)",
                true);
    }

    @Test
    public void testPriorOnComparisonStartIsDeparsed() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE PRIOR a = b", true);
    }

    @Test
    public void testPriorOnComparisonEndIsDeparsed() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE a = PRIOR b", true);
    }

    @Test
    public void testPriorIsNullShorthand() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a ISNULL");
        IsNullExpression isNull = (IsNullExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                isNull.getOraclePriorPosition());
    }

    @Test
    public void testPriorNotNullShorthand() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE PRIOR a NOTNULL", true);
    }

    @Test
    public void testPriorIsFalse() throws JSQLParserException {
        Expression where = whereOf("SELECT * FROM t WHERE PRIOR a IS FALSE");
        IsBooleanExpression isBoolean = (IsBooleanExpression) where;
        assertEquals(SupportsOldOracleJoinSyntax.ORACLE_PRIOR_START,
                isBoolean.getOraclePriorPosition());
    }

    @Test
    public void testPriorIsNotUnknown() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE PRIOR a IS NOT UNKNOWN",
                true);
    }

    @Test
    public void testPriorNotSimilarTo() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed(
                "SELECT * FROM t WHERE PRIOR a NOT SIMILAR TO 'x'",
                true);
    }

    @Test
    public void testPriorInJoinOnClause() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed(
                "SELECT * FROM t JOIN t2 ON PRIOR t.c = t2.c", true);
    }

    @Test
    public void testPriorBetweenEndOperandStaysIntact() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE a BETWEEN 1 AND PRIOR 2",
                true);
    }

    @Test
    public void testPlainConditionsWithoutPriorAreUnchanged() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE a = b", true);
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE a BETWEEN 1 AND 2", true);
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE a LIKE 'x%'", true);
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT * FROM t WHERE a IS NULL", true);
    }

    @Test
    public void testNotPriorBetween() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed(
                "SELECT * FROM t WHERE NOT PRIOR a BETWEEN 1 AND 2", true);
    }

    @Test
    public void testPriorOnSeveralConditions() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed(
                "SELECT * FROM t WHERE PRIOR a = b AND PRIOR c BETWEEN 1 AND 2", true);
    }

    @Test
    public void testPriorCombinedWithOracleJoinOnBetween() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed(
                "SELECT * FROM t1, t2 WHERE PRIOR t2.c(+) BETWEEN 1 AND 2", true);
    }
}
