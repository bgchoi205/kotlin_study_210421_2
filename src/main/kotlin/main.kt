import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var articles = mutableListOf<Article>()
var lastId = 0
val pageCount = 10

fun getArticleById(id : Int):Article?{
    var articleToDel : Article? = null

    for(article in articles){
        if(article.id == id){
            articleToDel = article
            return articleToDel
            continue
        }
    }
    return null
}

fun addArticle(title:String, body:String) : Int{
    val id = lastId + 1
    val regDate = Util.getRegDate()
    val updateDate = Util.getRegDate()

    val article = Article(id, title, body, regDate, updateDate)
    articles.add(article)
    lastId = id

    return id
}

fun makeTestArticles(){

    for(i in 1..50){
        val title = "제목 $i"
        val body = "내용 $i"
        addArticle(title, body)
    }
}

fun articlePageList(id : Int){
    val fromIndex = (id - 1) * 10
    val startIndex = articles.size - 1 - fromIndex
    val endIndex = startIndex - 10 + 1

    for(i in startIndex downTo endIndex){
        println("번호 : ${articles[i].id} / 작성날짜 : ${articles[i].regDate} / 제목 : ${articles[i].title}")
    }

}



fun main() {
    println("==프로그램 시작==")
    println("테스트 데이터 생성")
    makeTestArticles()

    while(true){
        print("명령어 입력 : ")
        val command = readLine()!!.trim()
        if(command == "exit"){
            break
        }
        else if(command == "article write"){

            print("제목 : ")
            val title = readLine()!!.trim()
            print("내용 : ")
            val body = readLine()!!.trim()
            val id = addArticle(title, body)

            println("$id 번 게시물이 등록되었습니다.")

        }
        else if(command.startsWith("article list ")){
            val id = command.trim().split(" ")[2].toInt()
            articlePageList(id)
        }
        else if(command.startsWith("article delete ")){
            val id = command.trim().split(" ")[2].toInt()

            var articleToDel = getArticleById(id)

            if(articleToDel == null){
                println("존재하지 않는 게시물 입니다.")
                continue
            }
            articles.remove(articleToDel)
            println("${id}번 게시물이 삭제되었습니다.")

        }
        else if(command.startsWith("article modify ")){
            val id = command.trim().split(" ")[2].toInt()

            var articleToMod = getArticleById(id)

            if(articleToMod == null){
                println("${id}번 게시물은 존재하지 않습니다.")
                continue
            }

            print("새 제목 : ")
            articleToMod.title = readLine()!!.trim()
            print("새 내용 : ")
            articleToMod.body = readLine()!!.trim()
            articleToMod.updateDate = Util.getRegDate()

            println("${id}번 게시물 수정이 완료되었습니다.")
        }
        else if(command.startsWith("article detail ")){
            val id = command.trim().split(" ")[2].toInt()

            var articleToDetail = getArticleById(id)

            if(articleToDetail == null){
                println("${id}번 게시물은 존재하지 않습니다.")
                continue
            }

            println("번호 : ${articleToDetail.id}")
            println("작성날짜 : ${articleToDetail.regDate}")
            println("갱신날짜 : ${articleToDetail.updateDate}")
            println("제목 : ${articleToDetail.title}")
            println("내용 : ${articleToDetail.body}")

        }

    }


    println("==프로그램 끝==")
}

data class Article(
    val id : Int,
    var title : String,
    var body : String,
    val regDate : String,
    var updateDate : String
){

}

object Util {
    fun getRegDate(): String {
        var now = LocalDateTime.now()

        var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분 ss초"))
        return Strnow
    }
}