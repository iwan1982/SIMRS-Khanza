/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanzahmsserviceaplicare;

import fungsi.SirsApi;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.Timer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 *
 * @author windiartonugroho
 */
public class frmUtama extends javax.swing.JFrame {
    private  Properties prop = new Properties();
    private  Connection koneksi=koneksiDB.condb();
    private  sekuel Sequel=new sekuel();
    private  String requestXML,URL="";
    private  SirsApi api=new SirsApi();
    private  HttpHeaders headers;
    private  HttpEntity requestEntity;
    private  PreparedStatement ps;
    private  ResultSet rs;
    private int totaltt=0,tersedia=0,menunggu=0,terpakai=0;

    /**
     * Creates new form frmUtama
     */
    public frmUtama() {
        initComponents();
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URL = prop.getProperty("URLAPISIRS");	
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
        this.setSize(390,340);
        
        jam();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TeksArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIMKES Khanza Service SIRANAP");

        TeksArea.setColumns(20);
        TeksArea.setRows(5);
        jScrollPane1.setViewportView(TeksArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TeksArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                Date now = Calendar.getInstance().getTime();
                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                nilai_jam = now.getHours();
                nilai_menit = now.getMinutes();
                nilai_detik = now.getSeconds();
                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                TeksArea.append(jam+":"+menit+":"+detik+"\n");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                if(menit.equals("01")&&detik.equals("01")){
                    if(jam.equals("01")&&menit.equals("01")&&detik.equals("01")){
                        TeksArea.setText("");
                    }
                        
                    try {
                        TeksArea.append("Memulai update Siranap\n");
                        ps=koneksi.prepareStatement(
                                "select siranap_ketersediaan_kamar.kode_ruang_siranap,siranap_ketersediaan_kamar.kelas_ruang_siranap,siranap_ketersediaan_kamar.kd_bangsal," +
                                "bangsal.nm_bangsal,siranap_ketersediaan_kamar.kelas,siranap_ketersediaan_kamar.kapasitas," +
                                "siranap_ketersediaan_kamar.tersedia,siranap_ketersediaan_kamar.tersediapria," +
                                "siranap_ketersediaan_kamar.tersediawanita,siranap_ketersediaan_kamar.tersediapriawanita " +
                                "from siranap_ketersediaan_kamar inner join bangsal on siranap_ketersediaan_kamar.kd_bangsal=bangsal.kd_bangsal");
                        try {
                            rs=ps.executeQuery();
                            while(rs.next()){
                                TeksArea.append("Mengirimkan kamar "+rs.getString("kode_ruang_siranap")+" "+rs.getString("nm_bangsal")+"\n");
                                try {    
                                    totaltt=Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and kd_bangsal='"+rs.getString("kd_bangsal")+"'");
                                    tersedia=Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and status='KOSONG' and kd_bangsal='"+rs.getString("kd_bangsal")+"'");
                                    terpakai=Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and status='ISI' and kd_bangsal='"+rs.getString("kd_bangsal")+"'");
                                    menunggu=Sequel.cariInteger("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and status='DIBERSIHKAN' and kd_bangsal='"+rs.getString("kd_bangsal")+"'");
                                    headers = new HttpHeaders();
                                    headers.add("X-rs-id",prop.getProperty("IDSIRS")); 
                                    headers.add("X-pass",api.getHmac()); 
                                    headers.add("Content-Type","application/xml; charset=ISO-8859-1");
                                    requestXML ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                                    "<xml>\n"+    
                                        "<data>\n"+
                                            "<kode_ruang>"+rs.getString("kelas_ruang_siranap").substring(0,4)+"</kode_ruang>\n"+
                                            "<tipe_pasien>"+rs.getString("kode_ruang_siranap").substring(0,4)+"</tipe_pasien>\n"+
                                            "<total_TT>"+Integer.toString(totaltt)+"</total_TT>\n"+
                                            "<terpakai_male>"+Integer.toString(terpakai)+"</terpakai_male>\n"+
                                            "<terpakai_female>"+Integer.toString(terpakai)+"</terpakai_female>\n"+
                                            "<kosong_male>"+Integer.toString(tersedia)+"</kosong_male>\n"+
                                            "<kosong_female>"+Integer.toString(tersedia)+"</kosong_female>\n"+
                                            "<waiting>"+Integer.toString(menunggu)+"</waiting>\n"+
                                            "<tgl_update>"+dateFormat.format(date)+"</tgl_update>\n"+
                                        "</data>\n"+
                                    "</xml>";              
                                    TeksArea.append("JSON dikirim : "+requestXML+"\n");
                                    requestEntity = new HttpEntity(requestXML,headers);
                                    requestXML=api.getRest().exchange(URL+"/ranap", HttpMethod.POST, requestEntity, String.class).getBody();
                                    TeksArea.append("respon WS BPJS : "+requestXML+"\n");
                                }catch (Exception ex) {
                                    System.out.println("Notifikasi Bridging : "+ex);
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Notif Ketersediaan : "+ex);
                        } finally{
                            if(rs!=null){
                                rs.close();
                            }
                            if(ps!=null){
                                ps.close();
                            }
                        }
                        TeksArea.append("Proses update selesai\n");
                    } catch (Exception ez) {
                        System.out.println("Notif : "+ez);
                    }
                }
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
}
