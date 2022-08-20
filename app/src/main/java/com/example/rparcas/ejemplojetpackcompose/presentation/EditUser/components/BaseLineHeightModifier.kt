package com.example.rparcas.ejemplojetpackcompose.presentation.EditUser.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp


/**
 * Clase para poder hacer que el hint suba cuando estas escribiendo
 */
data class BaseLineHeightModifier (
    val heightFromBaseLine: Dp,
): LayoutModifier{

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val textPlaceable = measurable.measure(constraints)
        val firstBaseLine = textPlaceable[FirstBaseline]
        val lastBaseLine = textPlaceable[LastBaseline]

        val height = heightFromBaseLine.roundToPx() + lastBaseLine - firstBaseLine

        return layout(constraints.maxWidth, height ){
            val topY = heightFromBaseLine.roundToPx() - firstBaseLine
            textPlaceable.place(0,topY)
        }
    }

}

/**
 * extendemos el objeto modifier para crearle una funcion nueva, le añadimos la de base line
 * para poder subir el hint cuando se escriba
 */
fun Modifier.baselineHeight(heightFromBaseLine: Dp):Modifier =
    this.then(BaseLineHeightModifier(heightFromBaseLine))