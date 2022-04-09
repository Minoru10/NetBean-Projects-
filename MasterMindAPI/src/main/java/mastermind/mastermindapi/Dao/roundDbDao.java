package mastermind.mastermindapi.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import mastermind.mastermindapi.Model.Game;
import mastermind.mastermindapi.Model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class roundDbDao implements roundDao {
    
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public roundDbDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ToDoMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("id"));
            round.setResult(rs.getString("Result"));
            round.setTimeStamp(rs.getString("TimePeriod"));
            return round;
        }
    }
    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO roundTbl(Guess, gameID) VALUES(?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, round.getGuess());
            statement.setInt(2, round.getGameID());
            
            
            
            return statement;

        }, keyHolder);

        round.setId(keyHolder.getKey().intValue());
        Round round2 = getRoundsForGame(round.getGameID()).stream()
                                              .filter(g -> g.getId() == round.getId())
                                              .findFirst()
                                              .get();
        round.setTimeStamp(round2.getTimeStamp());
        return round;
    }
// ================================== Get All rounds from the table =======================================
    @Override
    public List<Round> getAllRounds() {
        final String sql = "SELECT id, Result, TimePeriod, Guess, gameID FROM roundTbl;";
        return jdbcTemplate.query(sql, new roundDbDao.ToDoMapper());    
    }
// ================================== Get All rounds for the given gameID table =======================================
    @Override
    public List<Round> getRoundsForGame(int gameID) {
        
        final String sql = "SELECT id, Result, TimePeriod, Guess, gameID "
                + "FROM roundTbl WHERE gameID = " + gameID + ";";
        return jdbcTemplate.query(sql, new roundDbDao.ToDoMapper());
        
    }
    
}
