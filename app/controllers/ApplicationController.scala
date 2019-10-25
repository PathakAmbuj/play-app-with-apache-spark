package controllers

import bootstrap.Init
import javax.inject.Inject
import play.api.i18n.MessagesApi
import play.api.mvc.{Action, AnyContent, Controller}

import scala.concurrent.Future

class ApplicationController @Inject()(implicit webJarAssets: WebJarAssets,
                                      val messagesApi: MessagesApi) extends Controller {

  def index: Action[AnyContent] = {
    Action.async {

      val spark = Init.getSparkSessionInstance
      val df  = spark.sqlContext.read.json("conf/data.json").toDF()
      val result =  df.limit(100)

      val rawJson = result.toJSON.collectAsList()
      Future.successful(Ok(rawJson.toString))

    }
  }

  def getJson(filePath: String, outputCols: String, maxRow: String): Action[AnyContent] = {
    Action.async {

      val row = maxRow.toInt
      val cols = outputCols.split(",")
      val spark = Init.getSparkSessionInstance

      val ds  = spark.read.format("csv").option("header", "true")
        .load(filePath)

      val res = ds.select(cols.head, cols.tail: _*)

      val jsonData = res.limit(row).toJSON.collectAsList()
      Future.successful(Ok(jsonData.toString))
    }
  }
}



