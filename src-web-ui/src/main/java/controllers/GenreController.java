package controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import beans.Genre;
import db.Database;

@ManagedBean(eager = true)
@ApplicationScoped
public class GenreController implements Serializable {

    private ArrayList<Genre> genreList;

    public GenreController() {
        fillGenresAll();
    }

    private void fillGenresAll() {
        genreList = new ArrayList<Genre>();
//        Statement stmt = null;
//        ResultSet rs = null;
//        Connection conn = null;
//

//
//        try {
//            conn = Database.getConnection();
//
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("select * from flow_production.login order by email");
//            while (rs.next()) {
//                Genre genre = new Genre();
//                genre.setName(rs.getString("email"));
//                genre.setId(rs.getLong("id"));
//                genreList.add(genre);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(GenreController.class.getName()).log(Level.SEVERE, null, ex);
//        }

// -------------------------------------------------------------------------
      Genre genre = new Genre();
        genre.setName("diplom@bk.ru");
        genre.setId(1);
        genreList.add(genre);
// -------------------------------------------------------------------------


//        finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (rs != null) {
//                    rs.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(GenreController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }
}
