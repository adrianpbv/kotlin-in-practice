package adrian.refresher

interface IRefresherRepository {
    suspend fun getVendors(): List<String>

    suspend fun getVendorParts(vendor: String): List<String>

    suspend fun getInventory(vendor: String, part: String): Int

    suspend fun updateVendorParts(vendor: String, part: String, inv: Int): Boolean
}