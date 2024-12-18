package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    private DataSource dataSource;

    // The constructor
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    // get all categories ~DONE
    @Override
    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList<>();

        String query = """
                select * from categories
                """;
        try(Connection connection = getConnection()){

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet row = statement.executeQuery();

            while (row.next()){
                Category category = mapRow(row);
                categories.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // get all categories
        return categories;
    }
    // TODO get category by Id
    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        return null;
    }
    // TODO create method
    @Override
    public Category create(Category category)
    {
        // create a new category
        return null;
    }
    // TODO update method
    @Override
    public void update(int categoryId, Category category)
    {
        // update category
    }
    // TODO delete method
    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
