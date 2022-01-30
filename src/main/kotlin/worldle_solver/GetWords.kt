package worldle_solver

object GetResource {
    fun getIt(filename: String) = this.javaClass.classLoader.getResource(filename)!!.readText().trim().lines()
}
