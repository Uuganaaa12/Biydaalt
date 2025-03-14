Санамсаргүйгээр карт эрэмбэлэх:
java -jar target/flashcard-app-1.0-SNAPSHOT.jar --order random flashcards.txt

Буруу хариултуудыг эхэнд гаргах:
java -jar target/flashcard-app-1.0-SNAPSHOT.jar --order worst-first flashcards.txt

Сүүлийн алдаануудыг эхэнд гаргах:
java -jar target/flashcard-app-1.0-SNAPSHOT.jar --order recent-mistakes-first flashcards.txt

Зөв хариулт олон удаа шаардах:
java -jar target/flashcard-app-1.0-SNAPSHOT.jar --repetitions 3 flashcards.txt

Асуулт хариултуудын байрыг нь солих:
java -jar target/flashcard-app-1.0-SNAPSHOT.jar --invertCards flashcards.txt




Хэрвээ нэг төрлийн коммандыг зэрэг ашиглавал (жишээ нь : random  , worst-first) сүүлд бичсэн коммандыг дагах болно.

