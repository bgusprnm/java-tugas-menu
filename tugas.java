import java.util.Scanner;

// Kelas Menu untuk menyimpan data menu
class Menu {
    private String nama;
    private int harga;
    private String kategori;

    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public String getKategori() { return kategori; }
}



public class Main {

    // Daftar menu
    private static Menu[] menuItems = {
        new Menu("Nasi Goreng", 20000, "makanan"),
        new Menu("Ayam Bakar", 25000, "makanan"),
        new Menu("Mie Goreng", 18000, "makanan"),
        new Menu("Sate Ayam", 22000, "makanan"),
        new Menu("Soto Ayam", 21000, "makanan"),
        
        new Menu("Es Teh", 5000, "minuman"),
        new Menu("Es Jeruk", 7000, "minuman"),
        new Menu("Jus Alpukat", 15000, "minuman"),
        new Menu("Jus Mangga", 13000, "minuman"),
        new Menu("Teh Hangat", 4000, "minuman")
    };



    // Variabel untuk menyimpan pesanan
    private static String[] pesananNama = new String[4];
    private static int[] pesananJumlah = new int[4];
    private static int pesananCount = 0;



    public static void main(String[] args) {
        tampilkanMenu();
        prosesPesanan();
        hitungDanCetakStruk();
    }



    // Method untuk menampilkan daftar menu
    public static void tampilkanMenu() {
        System.out.println("== Daftar Menu Restoran ==");
    
    
    
        // Menampilkan makanan
        System.out.println("\n-- Makanan --");
        for (Menu menu : menuItems) {
            if (menu.getKategori().equalsIgnoreCase("makanan")) {
                System.out.println(menu.getNama() + " - Rp" + menu.getHarga());
            }
        }
    
    
    
        // Menampilkan minuman
        System.out.println("\n-- Minuman --");
        for (Menu menu : menuItems) {
            if (menu.getKategori().equalsIgnoreCase("minuman")) {
                System.out.println(menu.getNama() + " - Rp" + menu.getHarga());
            }
        }
    }



    // Method untuk memproses pemesanan
    public static void prosesPesanan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n== Masukkan Pesanan Anda (Maksimal 4 Menu) ==");

        while (pesananCount < 4) {
            System.out.print("Nama Menu (atau ketik 'selesai' untuk mengakhiri): ");
            String namaMenu = scanner.nextLine();

            if (namaMenu.equalsIgnoreCase("selesai")) break;



            // Validasi menu yang dipilih ada atau tidak
            if (!menuAda(namaMenu)) {
                System.out.println("Menu yang Anda pilih tidak ada. Silakan pilih menu yang tersedia.\n");
                continue; // Kembali ke awal loop untuk meminta input lagi
            }

            System.out.print("Jumlah: ");
            int jumlah = scanner.nextInt();
            scanner.nextLine();  // Membersihkan input buffer

            pesananNama[pesananCount] = namaMenu;
            pesananJumlah[pesananCount] = jumlah;
            pesananCount++;
        }
        scanner.close();
    }



    // Method untuk mengecek apakah nama menu ada di daftar menu
    public static boolean menuAda(String namaMenu) {
        for (Menu menu : menuItems) {
            if (menu.getNama().equalsIgnoreCase(namaMenu)) {
                return true; // Menu ditemukan
            }
        }
        return false; // Menu tidak ditemukan
    }



    // Method untuk menghitung total biaya dan mencetak struk
    public static void hitungDanCetakStruk() {
        int totalBiaya = 0;
        System.out.println("\n== Struk Pesanan Anda ==");



        // Menghitung biaya total pesanan
        for (int i = 0; i < pesananCount; i++) {
            String namaMenu = pesananNama[i];
            int jumlah = pesananJumlah[i];
            int hargaPerItem = cariHargaMenu(namaMenu);
            int totalHargaPerItem = hargaPerItem * jumlah;
            totalBiaya += totalHargaPerItem;

            System.out.println(namaMenu + " x" + jumlah + " - Rp" + totalHargaPerItem);
        }



        // Menambahkan pajak dan biaya pelayanan
        int pajak = totalBiaya * 10 / 100;
        int biayaPelayanan = 20000;
        totalBiaya += pajak + biayaPelayanan;



        // Diskon dan penawaran khusus
        int diskon = 0;
        if (totalBiaya > 100000) {
            diskon = totalBiaya * 10 / 100;
        } else if (totalBiaya > 50000 && adaPesananMinuman()) {
            System.out.println("Penawaran: Beli satu gratis satu untuk minuman.");
        }
        totalBiaya -= diskon;



        // Cetak rincian biaya
        System.out.println("Pajak (10%): Rp" + pajak);
        System.out.println("Biaya Pelayanan: Rp" + biayaPelayanan);
        if (diskon > 0) {
            System.out.println("Diskon (10%): -Rp" + diskon);
        }
        System.out.println("Total Biaya: Rp" + totalBiaya);
    }



    // Method untuk mencari harga menu berdasarkan nama
    public static int cariHargaMenu(String namaMenu) {
        for (Menu menu : menuItems) {
            if (menu.getNama().equalsIgnoreCase(namaMenu)) {
                return menu.getHarga();
            }
        }
        return 0; // Jika menu tidak ditemukan
    }



    // Method untuk mengecek apakah ada pesanan minuman
    public static boolean adaPesananMinuman() {
        for (int i = 0; i < pesananCount; i++) {
            String namaMenu = pesananNama[i];
            for (Menu menu : menuItems) {
                if (menu.getNama().equalsIgnoreCase(namaMenu) && menu.getKategori().equals("minuman")) {
                    return true;
                }
            }
        }
        return false;
    }
}

