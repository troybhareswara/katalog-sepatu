package com.troy.katalog_sepatu.data

import com.troy.katalog_sepatu.model.Shoe

object ShoeData {

    val allShoes: Array<Shoe> = arrayOf(
        // Kobe Bryant Series
        Shoe(
            id = 1,
            name = "Nike Kobe 6 Protro \"Mambacital\"",
            brand = "Kobe",
            price = "Rp 2.800.000",
            description = " Kobe 6 Protro Mambacital merayakan warisan Kobe Bryant dengan desain ikonik dan warna yang terinspirasi dari jersey Gigi. Dilengkapi dengan teknologi Nike React foam untuk responsivitas tinggi."
        ),
        Shoe(
            id = 2,
            name = "Nike Kobe 5 \"Bruce Lee\"",
            brand = "Kobe",
            price = "Rp 2.500.000",
            description = " Kobe 5 Bruce Lee menampilkan warna kuning emas dan aksen merah yang terinspirasi dari aksi legendario Bruce Lee. Desain low-top memberikan mobilitas dan kontrol terbaik di lapangan."
        ),
        Shoe(
            id = 3,
            name = "Nike Kobe 4 \"Gold Medal\"",
            brand = "Kobe",
            price = "Rp 2.400.000",
            description = " Kobe 4 Gold Medal merayakan pencapaian emas Kobe Bryant di Olympic Games. Desain elegan dengan detail金属 gold yang mencolok, sempurna untuk koleksi dan gameplay."
        ),

        // LeBron James Series
        Shoe(
            id = 4,
            name = "Nike LeBron 21",
            brand = "LeBron",
            price = "Rp 3.200.000",
            description = " LeBron 21 menawarkan stabilitas dan bantalan premium dengan Zoom Air unit penuh panjang. Desain futuristik denganmaterial ringan namun tahan lama untuk performa terbaik."
        ),
        Shoe(
            id = 5,
            name = "Nike LeBron 20 \"Cortez\"",
            brand = "LeBron",
            price = "Rp 2.800.000",
            description = " LeBron 20 Cortez Fusion menghadirkan kolaborasi eksklusif antara gaya Cortez klasik dan signature LeBron. Cocok untuk gaya hidup aktif dan olahraga."
        ),
        Shoe(
            id = 6,
            name = "Nike LeBron NXXT \"Dominate\"",
            brand = "LeBron",
            price = "Rp 2.600.000",
            description = " LeBron NXXT Dominate dirancang untuk pemain yang butuh kecepatan dan kontrol. Desain mid-top dengan traction outsole terbaik untuk perubahan arah cepat."
        ),

        // Sabrina Ionescu Series
        Shoe(
            id = 7,
            name = "Nike Sabrina 2 \"Full Quartz\"",
            brand = "Sabrina",
            price = "Rp 1.800.000",
            description = " Sabrina 2 Full Quartz menampilkan warna bening quartz yang elegan dan modern. Dirancang khusus untuk perempuan dengan fit yang optimal dan bantalan responsif."
        ),
        Shoe(
            id = 8,
            name = "Nike Sabrina 1 \"Cosmic\"",
            brand = "Sabrina",
            price = "Rp 1.600.000",
            description = " Sabrina 1 Cosmic terinspirasi dari luar angkasa dengan gradasi warna biru dan ungu yang mencolok. Ringan dan fleksibel untuk permainan Guards yang gesit."
        ),
        Shoe(
            id = 9,
            name = "Nike Sabrina 1 \"NYC HC\"",
            brand = "Sabrina",
            price = "Rp 1.700.000",
            description = " NYC HC Edition merayakan akar New York City Sabrina dengan warna iconic New York Knicks. Detail premium dengan logo khusus NYC HC di heel."
        )
    )

    fun getShoesByBrand(brand: String): Array<Shoe> {
        return allShoes.filter { it.brand == brand }.toTypedArray()
    }
}
