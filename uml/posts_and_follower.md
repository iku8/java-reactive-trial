@startuml
title 直列処理・並列処理とエラーハンドリングとリトライ

start

:APIリクエスト;

fork
:投稿一覧取得;
note right
:処理に3秒かかる
end note

fork again
repeat
:フォロワー取得;
repeat while (例外発生 && フォロワー取得試行回数 <= 1回)
note right
:処理に3秒かかる
:最大2回取得を試行する
end note

if (例外発生) then (true)
:空Listを作成して返す;
else (false)
:フォロワーのリストを返す;
endif

end fork

:投稿とフォロワー変換してレスポンス返却;

stop
@enduml
