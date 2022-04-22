package com.example.setsolo

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.setsolo.databinding.ItemCardBinding

class ItemAdapter(private val itemList:ArrayList<ItemData>,val mainActivity: MainActivity):RecyclerView.Adapter<ItemAdapter.CustomViewHolder>() {

    val TAG = "ItemAdapter"

    private lateinit var shareView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        shareView  =  view
        return CustomViewHolder(ItemCardBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ItemAdapter.CustomViewHolder, position: Int) {
        holder.number.text = itemList[position].number.toString()

        holder.itemView.setOnClickListener {
            val ownNumber = holder.number.text.toString().toInt()

            if(cardArrayAll[ownNumber].click){

                holder.itemView.setBackgroundResource(R.color.white)
                cardArrayAll[ownNumber].click = false

                for(i in 0 until chooseThreeCardArray.size){
                    if(chooseThreeCardArray[i]==ownNumber){
                        chooseThreeCardArray.removeAt(i)
                        threeCardPositionArray.removeAt(i)
                        break
                    }
                }
            }else{
                holder.itemView.setBackgroundResource(R.color.blue)
                cardArrayAll[ownNumber].click = true

                chooseThreeCardArray.add(ownNumber)
                threeCardPositionArray.add(position)
            }

            if(chooseThreeCardArray.size == 3){
                if((cardArrayAll[chooseThreeCardArray[0]].color == cardArrayAll[chooseThreeCardArray[1]].color &&
                            cardArrayAll[chooseThreeCardArray[1]].color == cardArrayAll[chooseThreeCardArray[2]].color)||
                    (cardArrayAll[chooseThreeCardArray[0]].color!= cardArrayAll[chooseThreeCardArray[1]].color&&
                            cardArrayAll[chooseThreeCardArray[1]].color!= cardArrayAll[chooseThreeCardArray[2]].color&&cardArrayAll[chooseThreeCardArray[0]].color!= cardArrayAll[chooseThreeCardArray[2]].color)){
                    if((cardArrayAll[chooseThreeCardArray[0]].state == cardArrayAll[chooseThreeCardArray[1]].state&&
                        cardArrayAll[chooseThreeCardArray[1]].state == cardArrayAll[chooseThreeCardArray[2]].state)||
                        (cardArrayAll[chooseThreeCardArray[0]].state != cardArrayAll[chooseThreeCardArray[1]].state&&
                                cardArrayAll[chooseThreeCardArray[1]].state != cardArrayAll[chooseThreeCardArray[2]].state&&cardArrayAll[chooseThreeCardArray[0]].state != cardArrayAll[chooseThreeCardArray[2]].state)){
                        if((cardArrayAll[chooseThreeCardArray[0]].shape == cardArrayAll[chooseThreeCardArray[1]].shape&&
                                    cardArrayAll[chooseThreeCardArray[1]].shape == cardArrayAll[chooseThreeCardArray[2]].shape)||
                            (cardArrayAll[chooseThreeCardArray[0]].shape != cardArrayAll[chooseThreeCardArray[1]].shape&&
                                    cardArrayAll[chooseThreeCardArray[1]].shape != cardArrayAll[chooseThreeCardArray[2]].shape&&cardArrayAll[chooseThreeCardArray[0]].shape != cardArrayAll[chooseThreeCardArray[2]].shape)){
                            if((cardArrayAll[chooseThreeCardArray[0]].num == cardArrayAll[chooseThreeCardArray[1]].num&&
                                        cardArrayAll[chooseThreeCardArray[1]].num == cardArrayAll[chooseThreeCardArray[2]].num)||
                                cardArrayAll[chooseThreeCardArray[0]].num != cardArrayAll[chooseThreeCardArray[1]].num&&
                                cardArrayAll[chooseThreeCardArray[1]].num != cardArrayAll[chooseThreeCardArray[2]].num&&cardArrayAll[chooseThreeCardArray[0]].num != cardArrayAll[chooseThreeCardArray[2]].num){

                                threeCardPositionArray.sort()
                                threeCardPositionArray.reverse()

                                itemList.removeAt(threeCardPositionArray[0])
                                notifyItemRemoved(threeCardPositionArray[0])
                                notifyItemRangeChanged(threeCardPositionArray[0], itemList.size)
                                itemList.removeAt(threeCardPositionArray[1])
                                notifyItemRemoved(threeCardPositionArray[1])
                                notifyItemRangeChanged(threeCardPositionArray[1], itemList.size)
                                itemList.removeAt(threeCardPositionArray[2])
                                notifyItemRemoved(threeCardPositionArray[2])
                                notifyItemRangeChanged(threeCardPositionArray[2], itemList.size)

                                threeCardPositionArray.clear()
                                chooseThreeCardArray.clear()

                                Toast.makeText(shareView.context,"정답!!",Toast.LENGTH_LONG).show()
                            }
                            else{
                                Toast.makeText(shareView.context,"숫자가 다름",Toast.LENGTH_SHORT).show()
                                cardArrayAll[chooseThreeCardArray[0]].click = false
                                cardArrayAll[chooseThreeCardArray[1]].click = false
                                cardArrayAll[chooseThreeCardArray[2]].click = false
                                mainActivity.change()
                            }
                        }
                        else{
                            Toast.makeText(shareView.context,"모양이 다름",Toast.LENGTH_SHORT).show()
                            cardArrayAll[chooseThreeCardArray[0]].click = false
                            cardArrayAll[chooseThreeCardArray[1]].click = false
                            cardArrayAll[chooseThreeCardArray[2]].click = false
                            mainActivity.change()
                        }
                    }
                    else{
                        Toast.makeText(shareView.context,"상태가 다름",Toast.LENGTH_SHORT).show()
                        cardArrayAll[chooseThreeCardArray[0]].click = false
                        cardArrayAll[chooseThreeCardArray[1]].click = false
                        cardArrayAll[chooseThreeCardArray[2]].click = false
                        mainActivity.change()
                    }
                }else{
                    Toast.makeText(shareView.context,"색이 다름",Toast.LENGTH_SHORT).show()
                    cardArrayAll[chooseThreeCardArray[0]].click = false
                    cardArrayAll[chooseThreeCardArray[1]].click = false
                    cardArrayAll[chooseThreeCardArray[2]].click = false
                    mainActivity.change()
                }
            }
        }

        holder.apply {
            bind()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class CustomViewHolder(val binding: ItemCardBinding):RecyclerView.ViewHolder(binding.root) {
        val number : TextView = itemView.findViewById(R.id.number)
        fun bind(){
            when(number.text.toString().toInt()+1){
                1-> binding.redZEmpty3.visibility = View.VISIBLE
                2->{
                    binding.redZEmpty2.visibility = View.VISIBLE
                    binding.redZEmpty4.visibility = View.VISIBLE
                }
                3->{
                    binding.redZEmpty1.visibility = View.VISIBLE
                    binding.redZEmpty3.visibility = View.VISIBLE
                    binding.redZEmpty5.visibility = View.VISIBLE
                }
                4->binding.purpleZEmpty3.visibility = View.VISIBLE
                5->{
                    binding.purpleZEmpty2.visibility = View.VISIBLE
                    binding.purpleZEmpty4.visibility = View.VISIBLE
                }
                6->{
                    binding.purpleZEmpty1.visibility = View.VISIBLE
                    binding.purpleZEmpty3.visibility = View.VISIBLE
                    binding.purpleZEmpty5.visibility = View.VISIBLE
                }
                7-> binding.greenZEmpty3.visibility = View.VISIBLE
                8->{
                    binding.greenZEmpty2.visibility = View.VISIBLE
                    binding.greenZEmpty4.visibility = View.VISIBLE
                }
                9->{
                    binding.greenZEmpty1.visibility = View.VISIBLE
                    binding.greenZEmpty3.visibility = View.VISIBLE
                    binding.greenZEmpty5.visibility = View.VISIBLE
                }
                10->binding.redRectangleEmpty3.visibility = View.VISIBLE
                11->{
                    binding.redRectangleEmpty2.visibility = View.VISIBLE
                    binding.redRectangleEmpty4.visibility = View.VISIBLE
                }
                12->{
                    binding.redRectangleEmpty1.visibility = View.VISIBLE
                    binding.redRectangleEmpty3.visibility = View.VISIBLE
                    binding.redRectangleEmpty5.visibility = View.VISIBLE
                }
                13->binding.purpleRectangleEmpty3.visibility = View.VISIBLE
                14->{
                    binding.purpleRectangleEmpty2.visibility = View.VISIBLE
                    binding.purpleRectangleEmpty4.visibility = View.VISIBLE
                }
                15->{
                    binding.purpleRectangleEmpty1.visibility = View.VISIBLE
                    binding.purpleRectangleEmpty3.visibility = View.VISIBLE
                    binding.purpleRectangleEmpty5.visibility = View.VISIBLE
                }
                16-> binding.greenRectangleEmpty3.visibility = View.VISIBLE
                17->{
                    binding.greenRectangleEmpty2.visibility = View.VISIBLE
                    binding.greenRectangleEmpty4.visibility = View.VISIBLE
                }
                18->{
                    binding.greenRectangleEmpty1.visibility = View.VISIBLE
                    binding.greenRectangleEmpty3.visibility = View.VISIBLE
                    binding.greenRectangleEmpty5.visibility = View.VISIBLE
                }
                19->binding.redCircleEmpty3.visibility = View.VISIBLE
                20->{
                    binding.redCircleEmpty2.visibility = View.VISIBLE
                    binding.redCircleEmpty4.visibility = View.VISIBLE
                }
                21->{
                    binding.redCircleEmpty1.visibility = View.VISIBLE
                    binding.redCircleEmpty3.visibility = View.VISIBLE
                    binding.redCircleEmpty5.visibility = View.VISIBLE
                }
                22->binding.purpleCircleEmpty3.visibility = View.VISIBLE
                23->{
                    binding.purpleCircleEmpty2.visibility = View.VISIBLE
                    binding.purpleCircleEmpty4.visibility = View.VISIBLE
                }
                24->{
                    binding.purpleCircleEmpty1.visibility = View.VISIBLE
                    binding.purpleCircleEmpty3.visibility = View.VISIBLE
                    binding.purpleCircleEmpty5.visibility = View.VISIBLE
                }
                25->binding.greenCircleEmpty3.visibility = View.VISIBLE
                26->{
                    binding.greenCircleEmpty2.visibility = View.VISIBLE
                    binding.greenCircleEmpty4.visibility = View.VISIBLE
                }
                27->{
                    binding.greenCircleEmpty1.visibility = View.VISIBLE
                    binding.greenCircleEmpty3.visibility = View.VISIBLE
                    binding.greenCircleEmpty5.visibility = View.VISIBLE
                }
                28->binding.redZLine3.visibility = View.VISIBLE
                29->{
                    binding.redZLine2.visibility = View.VISIBLE
                    binding.redZLine4.visibility = View.VISIBLE
                }
                30->{
                    binding.redZLine1.visibility = View.VISIBLE
                    binding.redZLine3.visibility = View.VISIBLE
                    binding.redZLine5.visibility = View.VISIBLE
                }
                31->binding.purpleZLine3.visibility = View.VISIBLE
                32->{
                    binding.purpleZLine2.visibility = View.VISIBLE
                    binding.purpleZLine4.visibility = View.VISIBLE
                }
                33->{
                    binding.purpleZLine1.visibility = View.VISIBLE
                    binding.purpleZLine3.visibility = View.VISIBLE
                    binding.purpleZLine5.visibility = View.VISIBLE
                }
                34->binding.greenZLine3.visibility = View.VISIBLE
                35->{
                    binding.greenZLine2.visibility = View.VISIBLE
                    binding.greenZLine4.visibility = View.VISIBLE
                }
                36->{
                    binding.greenZLine1.visibility = View.VISIBLE
                    binding.greenZLine3.visibility = View.VISIBLE
                    binding.greenZLine5.visibility = View.VISIBLE
                }
                37->binding.redRectangleLine3.visibility = View.VISIBLE
                38->{
                    binding.redRectangleLine2.visibility = View.VISIBLE
                    binding.redRectangleLine4.visibility = View.VISIBLE
                }
                39->{
                    binding.redRectangleLine1.visibility = View.VISIBLE
                    binding.redRectangleLine3.visibility = View.VISIBLE
                    binding.redRectangleLine5.visibility = View.VISIBLE
                }
                40->binding.purpleRectangleLine3.visibility = View.VISIBLE
                41->{
                    binding.purpleRectangleLine2.visibility = View.VISIBLE
                    binding.purpleRectangleLine4.visibility = View.VISIBLE
                }
                42->{
                    binding.purpleRectangleLine1.visibility = View.VISIBLE
                    binding.purpleRectangleLine3.visibility = View.VISIBLE
                    binding.purpleRectangleLine5.visibility = View.VISIBLE
                }
                43->binding.greenRectangleLine3.visibility = View.VISIBLE
                44->{
                    binding.greenRectangleLine2.visibility = View.VISIBLE
                    binding.greenRectangleLine4.visibility = View.VISIBLE
                }
                45->{
                    binding.greenRectangleLine1.visibility = View.VISIBLE
                    binding.greenRectangleLine3.visibility = View.VISIBLE
                    binding.greenRectangleLine5.visibility = View.VISIBLE
                }
                46->binding.redCircleLine3.visibility = View.VISIBLE
                47->{
                    binding.redCircleLine2.visibility = View.VISIBLE
                    binding.redCircleLine4.visibility = View.VISIBLE
                }
                48->{
                    binding.redCircleLine1.visibility = View.VISIBLE
                    binding.redCircleLine3.visibility = View.VISIBLE
                    binding.redCircleLine5.visibility = View.VISIBLE
                }
                49->binding.purpleCircleLine3.visibility = View.VISIBLE
                50->{
                    binding.purpleCircleLine2.visibility = View.VISIBLE
                    binding.purpleCircleLine4.visibility = View.VISIBLE
                }
                51->{
                    binding.purpleCircleLine1.visibility = View.VISIBLE
                    binding.purpleCircleLine3.visibility = View.VISIBLE
                    binding.purpleCircleLine5.visibility = View.VISIBLE
                }
                52->binding.greenCircleLine3.visibility = View.VISIBLE
                53->{
                    binding.greenCircleLine2.visibility = View.VISIBLE
                    binding.greenCircleLine4.visibility = View.VISIBLE
                }
                54->{
                    binding.greenCircleLine1.visibility = View.VISIBLE
                    binding.greenCircleLine3.visibility = View.VISIBLE
                    binding.greenCircleLine5.visibility = View.VISIBLE
                }
                55->binding.redZFill3.visibility = View.VISIBLE
                56->{
                    binding.redZFill2.visibility = View.VISIBLE
                    binding.redZFill4.visibility = View.VISIBLE
                }
                57->{
                    binding.redZFill1.visibility = View.VISIBLE
                    binding.redZFill3.visibility = View.VISIBLE
                    binding.redZFill5.visibility = View.VISIBLE
                }
                58->binding.purpleZFill3.visibility = View.VISIBLE
                59->{
                    binding.purpleZFill2.visibility = View.VISIBLE
                    binding.purpleZFill4.visibility = View.VISIBLE
                }
                60->{
                    binding.purpleZFill1.visibility = View.VISIBLE
                    binding.purpleZFill3.visibility = View.VISIBLE
                    binding.purpleZFill5.visibility = View.VISIBLE
                }
                61->binding.greenZFill3.visibility = View.VISIBLE
                62->{
                    binding.greenZFill2.visibility = View.VISIBLE
                    binding.greenZFill4.visibility = View.VISIBLE
                }
                63->{
                    binding.greenZFill1.visibility = View.VISIBLE
                    binding.greenZFill3.visibility = View.VISIBLE
                    binding.greenZFill5.visibility = View.VISIBLE
                }
                64->binding.redRectangleFill3.visibility = View.VISIBLE
                65->{
                    binding.redRectangleFill2.visibility = View.VISIBLE
                    binding.redRectangleFill4.visibility = View.VISIBLE
                }
                66 -> {
                    binding.redRectangleFill1.visibility = View.VISIBLE
                    binding.redRectangleFill3.visibility = View.VISIBLE
                    binding.redRectangleFill5.visibility = View.VISIBLE
                }
                67 -> binding.purpleRectangleFill3.visibility = View.VISIBLE
                68 -> {
                    binding.purpleRectangleFill2.visibility = View.VISIBLE
                    binding.purpleRectangleFill4.visibility = View.VISIBLE
                }
                69 -> {
                    binding.purpleRectangleFill1.visibility = View.VISIBLE
                    binding.purpleRectangleFill3.visibility = View.VISIBLE
                    binding.purpleRectangleFill5.visibility = View.VISIBLE
                }
                70 -> binding.greenRectangleFill3.visibility = View.VISIBLE
                71->{
                    binding.greenRectangleFill2.visibility = View.VISIBLE
                    binding.greenRectangleFill4.visibility = View.VISIBLE
                }
                72->{
                    binding.greenRectangleFill1.visibility = View.VISIBLE
                    binding.greenRectangleFill3.visibility = View.VISIBLE
                    binding.greenRectangleFill5.visibility = View.VISIBLE
                }
                73->binding.redCircleFill3.visibility = View.VISIBLE
                74->{
                    binding.redCircleFill2.visibility = View.VISIBLE
                    binding.redCircleFill4.visibility = View.VISIBLE
                }
                75->{
                    binding.redCircleFill1.visibility = View.VISIBLE
                    binding.redCircleFill3.visibility = View.VISIBLE
                    binding.redCircleFill5.visibility = View.VISIBLE
                }
                76->binding.purpleCircleFill3.visibility = View.VISIBLE
                77->{
                    binding.purpleCircleFill2.visibility = View.VISIBLE
                    binding.purpleCircleFill4.visibility = View.VISIBLE
                }
                78->{
                    binding.purpleCircleFill1.visibility = View.VISIBLE
                    binding.purpleCircleFill3.visibility = View.VISIBLE
                    binding.purpleCircleFill5.visibility = View.VISIBLE
                }
                79->binding.greenCircleFill3.visibility = View.VISIBLE
                80->{
                    binding.greenCircleFill2.visibility = View.VISIBLE
                    binding.greenCircleFill4.visibility = View.VISIBLE
                }
                81->{
                    binding.greenCircleFill1.visibility = View.VISIBLE
                    binding.greenCircleFill3.visibility = View.VISIBLE
                    binding.greenCircleFill5.visibility = View.VISIBLE
                }
            }


        }
    }


}