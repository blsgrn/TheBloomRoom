package com.example.thebloomroom_105_53;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentRecordAdapter extends RecyclerView.Adapter<PaymentRecordAdapter.PaymentRecordViewHolder> {

    private List<PaymentRecord> paymentRecordList;
    private Context context;

    public PaymentRecordAdapter(List<PaymentRecord> paymentRecordList, Context context) {
        this.paymentRecordList = paymentRecordList;
        this.context = context;
    }

    @NonNull
    @Override
    public PaymentRecordAdapter.PaymentRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_record, parent, false);
        return new PaymentRecordAdapter.PaymentRecordViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PaymentRecordAdapter.PaymentRecordViewHolder holder, int position) {
        PaymentRecord paymentRecord = paymentRecordList.get(position);

        holder.textViewReceiptNo.setText("Flower ID: " + String.valueOf(paymentRecord.getReceiptNo()));
        holder.textViewCustomer.setText("Flower Name: " + paymentRecord.getCustomer());
        holder.textViewAmountPaid.setText(String.format("Price: Rs %.2f", paymentRecord.getTotal()));
        holder.textViewPaymentMethod.setText("Category: " + paymentRecord.getMethod());

        final int currentPosition = position;


    }


    @Override
    public int getItemCount() {
        return paymentRecordList.size();
    }


    public class PaymentRecordViewHolder  extends RecyclerView.ViewHolder {
        TextView textViewReceiptNo, textViewCustomer, textViewAmountPaid, textViewPaymentMethod;
        ImageButton btnDeleteFlower;
        public PaymentRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewReceiptNo = itemView.findViewById(R.id.textViewReceiptNo);
            textViewCustomer = itemView.findViewById(R.id.textViewCustomer);
            textViewAmountPaid = itemView.findViewById(R.id.textViewAmountPaid);
            textViewPaymentMethod = itemView.findViewById(R.id.textViewPaymentMethod);

        }
    }

}

