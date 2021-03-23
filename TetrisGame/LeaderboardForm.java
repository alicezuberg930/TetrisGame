package TetrisGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class LeaderboardForm extends JFrame {

    private JLabel back;
    private JTable players;
    private DefaultTableModel model;
    private static String name;
    private String columns[] = {"No", "Name", "Score", "Level"};
    private static final String database = "jdbc:mysql://localhost:3306/java_project";
    private static final String username = "root";
    private static final String password = "";

    public LeaderboardForm() {
        this.setSize(650, 800);
        back = new JLabel();
        back.setBounds(10, 5, 50, 50);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        new SetIcon(back, "/Image/back.png");
        Object[][] data = new Object[][]{};
        final Class[] columnClass = new Class[]{
            Integer.class, String.class, int.class, int.class
        };
        model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        PrintPlayers();
        players = new JTable(model);
        players.setBounds(0, 60, this.getWidth(), this.getHeight());
        players.setBackground(new Color(207, 242, 247));
        players.setSelectionBackground(new Color(134, 228, 217));
        players.setRowHeight(30);
        JScrollPane scroll = new JScrollPane(players);
        scroll.setBounds(0, 70, 650, 800);
        this.add(scroll);
        this.add(back);
        setCellsAlignment(players, SwingConstants.CENTER);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Bảng điểm số");
        MainMenu();
    }

    public void setInvisible() {
        this.setVisible(false);
    }

    public void MainMenu() {
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.ShowMenu();
                setInvisible();
            }
        });
    }

    public void setCellsAlignment(JTable table, int alignment) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);
        TableModel tableModel = table.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    public void PrintPlayers() {
        Connection con = null;
        Statement st = null;
        try {
            con = DriverManager.getConnection(database, username, password);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from leaderboard order by score desc");
            int i = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    i++, rs.getString("name"), rs.getInt("score"), rs.getInt("level")
                });
            }
        } catch (SQLException ex) {

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void GameOver(int score, int level) {
        name = JOptionPane.showInputDialog("Game Over\nPlease Type your name");
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DriverManager.getConnection(database, username, password);
            st = con.prepareCall("insert into leaderboard(name, score, level) values(?,?,?)");
            st.setString(1, name);
            st.setInt(2, score);
            st.setInt(3, level);
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
