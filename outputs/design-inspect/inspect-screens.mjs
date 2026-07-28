import { FileBlob, SpreadsheetFile } from "@oai/artifact-tool";

const base = "C:/pleiades/employee-management/employee-system/outputs/design-inspect/";
const files = [
  "派遣会社従業員管理システム_設計成果物/02_基本設計/01_システム概要・機能一覧.xlsx",
  "派遣会社従業員管理システム_設計成果物/02_基本設計/03_画面基本設計.xlsx",
  "派遣会社従業員管理システム_設計成果物/03_詳細設計/01_画面項目・入力チェック設計.xlsx",
  "派遣会社従業員管理システム_設計成果物/03_詳細設計/03_機能処理詳細設計.xlsx"
];

for (const relative of files) {
  const input = await FileBlob.load(base + relative);
  const workbook = await SpreadsheetFile.importXlsx(input);
  console.log(`FILE: ${relative}`);
  console.log((await workbook.inspect({ kind: "sheet", include: "id,name", maxChars: 4000 })).ndjson);
  console.log((await workbook.inspect({
    kind: "match",
    searchTerm: "契約終了|契約終了日|contract_end_date|参画|案件履歴|SCR-005|SCR-006|SCR-007|assignment|ASSIGNMENT",
    options: { useRegex: true, maxResults: 150 },
    summary: "screen and contract search"
  })).ndjson);
}
