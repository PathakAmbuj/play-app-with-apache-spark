package controllers

import javax.inject.Inject
import play.api.i18n.MessagesApi
import play.api.mvc.Controller
import play.api.mvc._
import views.html.{jobPage, loginError, welcome}

import scala.concurrent.Future

class HomeController @Inject()(implicit webJarAssets: WebJarAssets,
                               val messagesApi: MessagesApi) extends Controller {


  def loginCheck(user: String, password: String): Action[AnyContent] = {
    Action.async {

      if (user.equals("ambuj") && password.equals("pathak")) {
        Future.successful(Ok(welcome.render()))

      } else {
        Future.successful(Ok(loginError.render()))
      }
    }
  }


  def jobpage: Action[AnyContent] = {
    Action.async {
      Future.successful(Ok(jobPage.render()))
    }
  }

}
