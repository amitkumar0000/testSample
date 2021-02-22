import com.google.gson.annotations.SerializedName

data class Players (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("icon") val icon : String
)