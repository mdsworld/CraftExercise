package com.craftexercise.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;

@Service
public class DemoRepository
{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //////////////////////////////////
    //      Domain calls fpr ITEM..
    ///////////////////////////////////
    public int addItem(Item item)
    {
        String sql = "INSERT INTO item(name, price, quantity, category) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql, item.getName(),
                item.getPrice(), item.getQuantity(), item.getCategory());
    }

    public int updateItem(Item item)
    {
        String sql = "UPDATE ITEM SET quantity=? where id=?";
        return jdbcTemplate.update(sql, item.getQuantity(), item.getItemId());
    }

    public Item findItemById(Integer id)
    {
        try{
            return jdbcTemplate.queryForObject(
                "SELECT * FROM item WHERE id=?", new Object[] { id }, new ItemRowMapper());
        }catch(DataAccessException dae){
            return null;
        }
    }

    public List<Item> getAllItems()
    {
        return jdbcTemplate.query("SELECT * FROM item", new ItemRowMapper());
    }

    //////////////////////////////////
    //      Domain calls for PROMO..
    ///////////////////////////////////
    public int addPromo(Promo promo)
    {
        String sql = "INSERT INTO Promo(promo_code, promo_amt, notes) VALUES(?,?,?)";
        return jdbcTemplate.update(sql, promo.getPromoCode(), promo.getPromoAmt(), promo.getDescription());
    }

    public Promo findPromoByCode(String promoCode)
    {
        try{
            return jdbcTemplate.queryForObject(
                "SELECT * FROM PROMO WHERE promo_code=?", new Object[] { promoCode }, new RowMapper<Promo>()
                {
                    public Promo mapRow(ResultSet rs, int arg1) throws SQLException
                    {
                        Promo promo = new Promo();
                        promo.setPromoId(rs.getInt("id"));
                        promo.setPromoCode(rs.getString("promo_code"));
                        promo.setPromoAmt(rs.getBigDecimal("promo_amt"));
                        promo.setDescription(rs.getString("notes"));
                        return promo;
                    }
                });
        }catch(DataAccessException dae){
            return null;
        }
    }
    
    //////////////////////////////////
    //      Domain calls for ORDER
    ///////////////////////////////////

    public Order addOrder(Order order)
    {
        final String sql = "insert into ORDER_TR (item_ids, promo_id, order_num, total_amt) values(?, ?, ?, ?)";
        
        int state = jdbcTemplate.update(sql, order.getItemIds(),
                order.getPromoId(), order.getOrderNumber(), order.getTotalPrice());
        if(state < 0)
            return null;
        return order;
    }


    // Only for in-memory DBs.
    @Bean
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema-hsqldb.sql")
                .addScript("classpath:data-hsqldb.sql")
                .build();
    }
}
    
    class ItemRowMapper implements RowMapper<Item>{
        public Item mapRow(ResultSet rs, int arg1) throws SQLException
        {
            Item item = new Item();
            item.setItemId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getBigDecimal("price"));
            item.setQuantity(rs.getLong("quantity"));
            item.setCategory(rs.getString("category"));
            return item;
        }
    }
