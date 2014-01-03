package de.milltree.liquibaseexample.extensions;

import liquibase.change.custom.CustomSqlChange;
import liquibase.change.custom.CustomSqlRollback;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.UpdateStatement;

/**
 * Example implementation of a {@link CustomSqlChange} which produces a set of
 * SQL statements to be executed.
 * <p>
 * Additionally this class could implement {@link CustomSqlRollback} for
 * providing default rollback statements. However in this case no useful default
 * rollback exists.
 * </p>
 */
public class UpdateGroupStock implements CustomSqlChange {

	@SuppressWarnings("unused")
	private ResourceAccessor resourceAccessor;

	private String productGroupName;

	private Integer newStockAmount;

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public Integer getNewStockAmount() {
		return newStockAmount;
	}

	public void setNewStockAmount(Integer newStockAmount) {
		this.newStockAmount = newStockAmount;
	}

	@Override
	public String getConfirmationMessage() {
		return "Stock updated to " + newStockAmount
				+ " for all products in product group " + productGroupName
				+ ".";
	}

	@Override
	public void setUp() throws SetupException {
	}

	@Override
	public void setFileOpener(ResourceAccessor resourceAccessor) {
		this.resourceAccessor = resourceAccessor;
	}

	@Override
	public ValidationErrors validate(Database database) {
		ValidationErrors errors = new ValidationErrors();
		errors.checkRequiredField("productGroupName", productGroupName);
		errors.checkRequiredField("newStockAmount", newStockAmount);
		return errors;
	}

	@Override
	public SqlStatement[] generateStatements(Database database)
			throws CustomChangeException {
		UpdateStatement updateStock = new UpdateStatement(
				database.getDefaultCatalogName(),
				database.getDefaultSchemaName(), "stockentry");
		updateStock.addNewColumnValue("amount", newStockAmount);
		updateStock
				.setWhereClause("product_id IN (SELECT p.id FROM product p WHERE p.productgroup_id IN ("
						+ "SELECT g.id FROM productgroup g WHERE g.name = '"
						+ productGroupName + "'))");

		return new SqlStatement[] { updateStock };
	}

}
