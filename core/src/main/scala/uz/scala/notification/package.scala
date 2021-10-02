package uz.scala

import scalacss.defaults.Exports
import scalacss.internal.mutable.Settings

package object notification {
  sealed trait NotificationType

  case object Success extends NotificationType

  case object Warn extends NotificationType

  case object Fail extends NotificationType

  case object Info extends NotificationType
  val CssSettings: Exports with Settings = scalacss.devOrProdDefaults
}
