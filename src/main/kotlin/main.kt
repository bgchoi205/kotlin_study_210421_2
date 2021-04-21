
fun main() {
    println("==프로그램 시작==")

    var lastId = 0
    var articles = mutableListOf<Article>()

    while(true){
        print("명령어 입력 : ")
        val command = readLine()!!.trim()
        if(command == "exit"){
            break
        }
        else if(command == "article write"){
            val id = lastId + 1
            print("제목 : ")
            val title = readLine()!!.trim()
            print("내용 : ")
            val body = readLine()!!.trim()

            val article = Article(id, title, body)
            articles.add(article)

            println("$id 번 게시물이 등록되었습니다.")

            lastId = id
        }

    }


    println("==프로그램 끝==")
}

data class Article(
    val id : Int,
    val title : String,
    val body : String
){

}
