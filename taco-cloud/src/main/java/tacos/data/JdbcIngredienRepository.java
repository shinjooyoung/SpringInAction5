package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredienRepository implements IngredientRepository{
	
	// 인스턴스 객체
	private JdbcTemplate jdbc;
	
	// 생성자의 @Autowired 애노테이션으로 스프링 컨텍스트에 의해 JdbcTemplate의 빈이 주입된다.
	@Autowired
	public JdbcIngredienRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findById(String id) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select id, name, type from Ingredient where id =?",this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		// TODO Auto-generated method stub
		jdbc.update("insert into Ingredient (id, name, type) values (?,?,?)", ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}

}
