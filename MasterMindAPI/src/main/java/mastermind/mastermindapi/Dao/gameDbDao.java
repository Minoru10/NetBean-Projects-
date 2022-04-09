package mastermind.mastermindapi.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import mastermind.mastermindapi.Model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class gameDbDao implements gameDao{
    
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public gameDbDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class ToDoMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setInProgress(rs.getBoolean("inProgress"));
            game.setAnswer(rs.getString("answer"));
            return game;
        }
    }
// ================================== create and add Game in the table =======================================    
    @Override
    public Game createGame(Game game) {
        final String sql = "INSERT INTO gameTbl(inProgress, answer) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(2, game.getAnswer());
            statement.setBoolean(1, game.isInProgress());
            
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }
// ================================== Get All Games in the table =======================================
    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT id, inProgress, answer FROM gameTbl;";
        return jdbcTemplate.query(sql, new ToDoMapper());
    }
    @Override
    public Game getGameById(int id) {
        final String sql = "SELECT id, inProgress, answer "
                + "FROM gameTbl WHERE id = ?;";

        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), id);
    }
    
    
    
    
    
    
    

    
    
}
