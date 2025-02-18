module Main where
import System.Directory

import Control.Exception (try, IOException)
import System.IO (readFile)

main :: IO ()
main = do
    result <- try (readFile "input.txt") :: IO (Either IOException String)
    case result of
        Left e -> putStrLn $ "Error: " ++ show e
        Right content -> putStrLn content

printCurrentDirectory :: IO ()
printCurrentDirectory = do
    -- Получаем текущую директорию
    currentDir <- getCurrentDirectory
    -- Получаем список всех файлов и директорий в текущей директории
    contents <- getDirectoryContents currentDir
    -- Фильтруем список, чтобы исключить "." и ".."
    let files = filter (`notElem` [".", ".."]) contents
    -- Выводим список файлов
    mapM_ putStrLn files
    