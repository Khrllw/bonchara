main :: IO ()
main = do
  putStrLn "Enter :"  -- Выводим приглашение для ввода
  input <- getLine           -- Считываем строку с клавиатуры
  let number = (read :: String -> Int) input  -- Преобразуем строку в число
  putStrLn ("Вы ввели: " ++ show number)  -- Выводим число