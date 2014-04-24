package zinchenko.refactoring;

import java.sql.*;

public class FirstImpl {

    private Connection con;

    private Connection getConection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ref", "root", "root");
        connection.setAutoCommit(false);
        return connection;
    }

    public void process(int areaid, Date somedate, Date valuationdate) throws Exception{
        try {
            con = getConection();
            PreparedStatement st1 = con.prepareStatement("select accountid" + " from area_accounts" + " where areaid = ?");
            ResultSet rs1;

            PreparedStatement st2 = con.prepareStatement("select txid,amount,curr"
                    + " from transactions where accountid=?"
                    + " and txdate >= date_sub(?, interval 30 day) order by txdate");
            ResultSet rs2 = null;
            PreparedStatement st3 = con.prepareStatement("insert into check_log(txid,"
                    + " conv_amount)"
                    + " values(?,?)");

            st1.setInt(1, areaid);
            rs1 = st1.executeQuery();
            while (rs1.next()) {
                long accountid = rs1.getLong(1);
                st2.setLong(1, accountid);
                st2.setDate(2, somedate);
                rs2 = st2.executeQuery();
                while (rs2.next()) {
                    long txid = rs2.getLong(1);
                    float amount = rs2.getFloat(2);
                    String curr = rs2.getString(3);
                    if (AboveThreshold(amount, curr)) {
                        float conv_amount = Convert(amount, curr, valuationdate);
                        st3.setLong(1, txid);
                        st3.setFloat(2, conv_amount);
                        int dummy = st3.executeUpdate();
                    }
                }
            }

            rs1.close();
            st1.close();
            if (rs2 != null) {
                rs2.close();
            }
            st2.close();
            st3.close();
        } catch (SQLException ex) {
            System.err.println("==> SQLException: ");
            while (ex != null) {
                System.out.println("Message:" + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");

            }
        }
    }

    private boolean AboveThreshold(float amount, String iso) throws Exception {
        PreparedStatement thresholdstmt = con.prepareStatement("select threshold from thresholds where iso=?");
        ResultSet rs;
        boolean returnval = false;
        thresholdstmt.setString(1, iso);
        rs = thresholdstmt.executeQuery();
        if (rs.next()) {
            if (amount >= rs.getFloat(1)) {
                returnval = true;
            } else {
                returnval = false;
            }
        } else {
            // not found - assume no problem
            returnval = false;
        }
        if (rs != null) {
            rs.close();
        }
        thresholdstmt.close();
        return returnval;
    }

    private float Convert(float amount, String iso, Date valuationdate) throws Exception {
        PreparedStatement conversionstmt = con.prepareStatement("select ? * rate from currency_rates where iso = ? and rate_date = ?");
        ResultSet rs;
        float val = (float) 0.0;
        conversionstmt.setFloat(1, amount);
        conversionstmt.setString(2, iso);
        conversionstmt.setDate(3, valuationdate);
        rs = conversionstmt.executeQuery();
        if (rs.next()) {
            val = rs.getFloat(1);
        }
        if (rs != null) {
            rs.close();
        }
        conversionstmt.close();
        return val;
    }
}
