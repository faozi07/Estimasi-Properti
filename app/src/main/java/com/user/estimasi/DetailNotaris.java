package com.user.estimasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailNotaris extends AppCompatActivity {

    RecyclerView rvNotaris;
    public static ArrayList<modNotaris> arrNotaris = new ArrayList<>();
    public static int posisi = 0;
    NotarisAdapter notarisAdapter;
    //  ==========================================================  Jakarta ========================
    String[] namaNotaris = {"Notaris Achmad Abid,SH",
            "Notaris & PPAT Hj. Huriah Sadeli, SH",
            "Notaris & PPAT Mira Ruslim, SH",
            "Notaris & PPAT Ny. Soehardjo Hadie Widyokusumo, SH",
            "Notaris & PPAT R. Ay. Poppy Darmawan, SH"};

    String[] alamatNotaris = {"Plaza Central Suite 916\nJl. Jend. Sudirman No. 47, Jakarta Selatan",
            "Jl. Bukit Duri Tanjakan No. 98B, RT 004/009, Bukit Duri, Tebet, Jakarta Selatan",
            "Perumahan Pesanggrahan Mas Blok X No. 21 Jakarta Selatan",
            "Jl. Casablanca Raya No. 99, Tebet, Jakarta Selatan",
            "Jl. Wijaya I No. 75 Kebayoran Baru, Jakarta Selatan"};

    String[] notelpNotaris = {"021-5254181 / 021-5207990",
            "021-8298341 / 0816962924",
            "021-7363093",
            "021-83703225 / 021-83703057",
            "021-7206923 / 021-72787977"};

    //  ==========================================================  Bogor ========================
    String[] namaNotarisBog = {"Notaris-PPAT Diana Lies Fitriasari, S.H, M.Kn",
            "Notaris & PPAT Henry Susanto, SH",
            "Notaris Mulyani Syafei, SH",
            "Notaris & PPAT Reny Andriany, SH",
            "Notaris Dwi Swandiani, SH"};

    String[] alamatNotarisBog = {"Jl. Raya Jakarta-Bogor Km 44, Cibinong, Kab. Bogor",
            "Jl. Sawo Jajar Blok F/22, Pabaton, Bogor Tengah, Kota Bogor",
            "Jl. Ir. H. Djuanda No. 34, Paledang, Bogor Tengah, Kota Bogor",
            "Jl. Padjadjaran No. 21, Baranangsiang, Bogor Timur, Kota Bogor",
            "Jl. Siliwangi No. 58, Bondongan, Bogor Selatan, Kota Bogor"};

    String[] notelpNotarisBog = {"021-87906213",
            "0251-352828",
            "0251-373713",
            "0251-310032",
            "0251-8326861"};

    //  ==========================================================  Depok ========================
    String[] namaNotarisDep = {"Notaris & PPAT Maghdalia",
            "Notaris & PPAT Lucky Astuti Ichwan, SH",
            "Notaris & PPAT Wirastuti Puntaraksma, SH",
            "Notaris & PPAT Ismiati Dwi Rahayu, SH",
            "Notaris & PPAT Hendrawati Yuripersana, SH"};

    String[] alamatNotarisDep = {"B-1, Jl. Margonda Raya No. 23, Pd. Cina, Beji, Kota Depok",
            "Jl. Raya Muchtar Komplek Depok Maharaja Blok A-1/5 RT 004/04, Rangkapan Jaya, Pancoran MAS, Kota Depok",
            "Jl. Margonda Raya 158 RT 002/08, Pancoran MAS, Kemiri Muka, Beji, Kota Depok",
            "Jl. Raya Margonda, Kemiri Muka, Beji, Kota Depok",
            "Jl. Cinere Raya BI M/4416514, Cinere, Kota Depok"};

    String[] notelpNotarisDep = {"021-7753586",
            "0251-7790653",
            "0251-7752375",
            "0251-7520669",
            "0251-7549104"};

    //  ==========================================================  Tangerang ======================
    String[] namaNotarisTan = {"Notaris & PPAT Asep dudi Suwardi, SH",
            "Notaris & PPAT Dr. Gunawan Djajaputra, SH, SS., MH",
            "Notaris & PPAT Ellu Puspita Sunarya, SH",
            "Notaris & PPAT Hafni Istiqomah, SH",
            "Notaris & PPAT Hendrawati Yuripersana, SH"};

    String[] alamatNotarisTan = {"Jl. Raya Pamulang 2, Kota Tangerang Selatan",
            "Jl. Bintaro Utama 3A, Blok DD 15, No. 7 Lt.2, Bintaro Jaya, Sektor 3, Kota Tangerang",
            "Jl. Raya Serpong WTC Matahari Ruko No. 5819, Kota Tangerang",
            "Jl. Haji Mencong IX No. 9 RT 01/10 Kel. Sudimara Timur, Kec. Ciledug, Kota Tangerang",
            "Jl. Veteran Blok D13 No. 11 Komp. Kehakiman, Kota Tengerang"};

    String[] notelpNotarisTan = {"021-7753586",
            "08161981907",
            "0251-53155737",
            "0251-73458241",
            "0251-55331478"};

    //  ==========================================================  Bekasi ========================
    String[] namaNotarisBek = {"Notaris Rosdiana, SH",
            "Notaris Raita Varia Siregar",
            "Kantor Notaris & PPAT Mardiana",
            "Notaris & PPAT Mayya Dewanti, SH",
            "Notaris & PPAT Irmik, SH"};

    String[] alamatNotarisBek = {"Jl. Bintara Raya Blok Aa 1 No. 5, Kranji, Bekasi Barat, Kota Bekasi",
            "Jl. Bintara Raya No. 1A, Bintara, Bekasi Barat, Kota Bekasi",
            "Jl. Bintara Raya No. 25, Bintara, Bekasi Barat, Kota Bekasi",
            "Jl. Jend. Sudirman No. 16, Kranji, Bekasi Barat, Kota Bekasi",
            "Jl. Jend. A Yani Kayuringin Jaya Bekasi Selatan, Kota Bekasi"};

    String[] notelpNotarisBek = {"021-88966986",
            "0251-8868644",
            "0251-8624812",
            "0251-8853346",
            "0251-8852771"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notaris);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Notaris");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
        action();
    }

    private void init() {
        rvNotaris = findViewById(R.id.rvNotaris);

        notarisAdapter = new NotarisAdapter(this, arrNotaris);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvNotaris.setLayoutManager(llm);
        rvNotaris.setHasFixedSize(true);
        rvNotaris.setAdapter(notarisAdapter);
    }

    private void action() {
        for (int i=0; i<namaNotaris.length;i++) {
            modNotaris mn = new modNotaris();
            if (posisi == 0) {
                mn.setNama(namaNotaris[i]);
                mn.setAlamat(alamatNotaris[i]);
                mn.setNotelp(notelpNotaris[i]);
            }
            else if (posisi == 1) {
                mn.setNama(namaNotarisBog[i]);
                mn.setAlamat(alamatNotarisBog[i]);
                mn.setNotelp(notelpNotarisBog[i]);
            }
            else if (posisi == 2) {
                mn.setNama(namaNotarisDep[i]);
                mn.setAlamat(alamatNotarisDep[i]);
                mn.setNotelp(notelpNotarisDep[i]);
            }
            else if (posisi == 3) {
                mn.setNama(namaNotarisTan[i]);
                mn.setAlamat(alamatNotarisTan[i]);
                mn.setNotelp(notelpNotarisTan[i]);
            }
            else if (posisi == 4) {
                mn.setNama(namaNotarisBek[i]);
                mn.setAlamat(alamatNotarisBek[i]);
                mn.setNotelp(notelpNotarisBek[i]);
            }
            arrNotaris.add(mn);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        arrNotaris.clear();
        SoundBtn.soundBtn(DetailNotaris.this);
        finish();
    }
}
