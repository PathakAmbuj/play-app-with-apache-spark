package controllers

import javax.inject.Inject
import play.api.i18n.MessagesApi
import play.api.mvc.{Action, AnyContent, Controller}
import views.html.loginPage

import scala.concurrent.Future


class LoginController @Inject()(implicit webJarAssets: WebJarAssets,
                                val messagesApi: MessagesApi) extends Controller {

  def login: Action[AnyContent] =
  {
    Action.async {
      Future.successful(Ok(loginPage.render))
    }
  }
}
