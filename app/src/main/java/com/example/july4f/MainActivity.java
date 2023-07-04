package com.example.july4f;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView home = (TextView) findViewById(R.id.hello);

        // link do banco
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // referência à raiz do banco
        DatabaseReference raiz = database.getReference();

        // referência ao ramo de usuários (/usuários)
        DatabaseReference usuarios = raiz.child("usuários");

        // insere um campo nome com valor Victor e um campo ano com valor 1998 no ramo usuários
        usuarios.child("001").child("nome").setValue("Victor");
        usuarios.child("001").child("senha").setValue("123");

        usuarios.child("002").child("nome").setValue("Lucca");
        usuarios.child("002").child("senha").setValue("456");

        // para leitura do banco
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // recupera o valor do campo nome em usuário, converte para string e põe em home
                for(DataSnapshot usuario: snapshot.getChildren()) {
                    String data;
                    data = String.format(""
                            + "\n Nome: " + usuario.child("nome").getValue().toString()
                            + "\n Senha: " + usuario.child("senha").getValue().toString()
                    );

                    home.setText(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}