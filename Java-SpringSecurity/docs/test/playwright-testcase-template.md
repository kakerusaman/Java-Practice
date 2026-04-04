# Playwright向けテストケース設計テンプレート

## 概要

本ドキュメントは、設計書からAIがPlaywrightテストコードを生成しやすくするためのテストケーステンプレートです。
従来のテストケース項目に加え、Playwrightの操作・検証メソッドに直結する情報を含めています。

---

## テストケース基本情報

| 項目 | 内容 | 記入例 |
|---|---|---|
| テストケースID | ユニークな識別子 | `TC-LOGIN-001` |
| テスト名 | テストの概要がわかる名称 | 正常系：メールアドレスとパスワードでログインできる |
| 対象機能 | テスト対象の機能名 | ログイン機能 |
| 対象ページURL | テスト開始ページのパス | `/login` |
| 前提条件 | テスト実行前に必要な状態 | ユーザーがログアウト状態であること |
| 優先度 | High / Medium / Low | High |
| タグ | フィルタ実行用のラベル | `@smoke`, `@login`, `@regression` |

---

## テストデータ

### 入力データ

テスト中に画面から入力するデータを記載します。

| データ名 | 値 | 備考 |
|---|---|---|
| メールアドレス | `test@example.com` | 登録済みユーザー |
| パスワード | `Password123!` | 正しいパスワード |

### 事前準備データ

テスト実行前にDB・APIで準備が必要なデータを記載します。

| データ名 | 準備方法 | 内容 |
|---|---|---|
| テストユーザー | API (`POST /api/users`) | `{ "email": "test@example.com", "password": "Password123!" }` |

### パラメータバリエーション

パラメータ化テスト用に正常系・異常系・境界値をセットで記載します。

| パターン | メールアドレス | パスワード | 期待結果 |
|---|---|---|---|
| 正常系 | `test@example.com` | `Password123!` | ログイン成功、ダッシュボードへ遷移 |
| 異常系：パスワード誤り | `test@example.com` | `WrongPass` | エラーメッセージ表示 |
| 異常系：未入力 | （空） | （空） | バリデーションエラー表示 |
| 境界値：最大文字数 | 255文字のメールアドレス | `Password123!` | 仕様に応じた挙動 |

---

## 操作ステップ

各ステップをPlaywrightのメソッドに対応する粒度で記載します。

### ステップ一覧

| No. | 操作種別 | 対象ロケータ | 入力値 / パラメータ | 待機条件 | 備考 |
|---|---|---|---|---|---|
| 1 | goto | — | `/login` | ページの読み込み完了 | テスト開始 |
| 2 | fill | `data-testid="email-input"` | `test@example.com` | — | メールアドレス入力 |
| 3 | fill | `data-testid="password-input"` | `Password123!` | — | パスワード入力 |
| 4 | click | `data-testid="login-button"` | — | URLが `/dashboard` に変わるまで | ログインボタン押下 |

### 操作種別の凡例

| 操作種別 | Playwrightメソッド | 説明 |
|---|---|---|
| goto | `page.goto()` | ページ遷移 |
| click | `locator.click()` | クリック |
| fill | `locator.fill()` | テキスト入力（既存値をクリアして入力） |
| select | `locator.selectOption()` | セレクトボックス選択 |
| check | `locator.check()` | チェックボックスをON |
| uncheck | `locator.uncheck()` | チェックボックスをOFF |
| hover | `locator.hover()` | マウスホバー |
| press | `locator.press()` | キー押下（Enter, Tab など） |
| upload | `locator.setInputFiles()` | ファイルアップロード |
| drag | `locator.dragTo()` | ドラッグ＆ドロップ |
| scroll | `page.mouse.wheel()` | スクロール操作 |

---

## ロケータ戦略

要素の特定には以下の優先順位でロケータを使用します。

| 優先度 | ロケータ種別 | 記述例 | 説明 |
|---|---|---|---|
| 1（推奨） | data-testid | `data-testid="submit-btn"` | テスト専用属性。最も安定 |
| 2 | role + name | `role=button[name="送信"]` | アクセシビリティ属性ベース |
| 3 | placeholder | `placeholder="メールアドレス"` | 入力欄に有効 |
| 4 | text | `text="ログイン"` | 表示テキストで特定 |
| 5 | CSSセレクタ | `#email-input`, `.btn-primary` | 他の手段がない場合のみ |

> **推奨事項**: 設計・開発の段階で `data-testid` 属性を各操作対象要素に付与しておくことを強く推奨します。

---

## 検証（アサーション）

各ステップまたはテスト末尾で行う検証を記載します。

| No. | 検証種別 | 対象ロケータ | 期待値 | 備考 |
|---|---|---|---|---|
| 1 | toHaveURL | — | `/dashboard` | ログイン後のリダイレクト先 |
| 2 | toBeVisible | `data-testid="user-menu"` | — | ユーザーメニューが表示されること |
| 3 | toHaveText | `data-testid="welcome-message"` | `ようこそ、テストユーザーさん` | ウェルカムメッセージの内容 |

### 検証種別の凡例

| 検証種別 | Playwrightメソッド | 説明 |
|---|---|---|
| toBeVisible | `expect(locator).toBeVisible()` | 要素が表示されている |
| toBeHidden | `expect(locator).toBeHidden()` | 要素が非表示 |
| toHaveText | `expect(locator).toHaveText()` | テキスト内容が一致 |
| toContainText | `expect(locator).toContainText()` | テキストを含む |
| toHaveValue | `expect(locator).toHaveValue()` | input要素の値が一致 |
| toHaveURL | `expect(page).toHaveURL()` | ページURLが一致 |
| toHaveCount | `expect(locator).toHaveCount()` | 要素の個数が一致 |
| toBeDisabled | `expect(locator).toBeDisabled()` | 要素が無効状態 |
| toBeEnabled | `expect(locator).toBeEnabled()` | 要素が有効状態 |
| toBeChecked | `expect(locator).toBeChecked()` | チェックされている |
| toHaveAttribute | `expect(locator).toHaveAttribute()` | 属性値が一致 |
| toHaveCSS | `expect(locator).toHaveCSS()` | CSSプロパティが一致 |

---

## 待機条件

SPAやAPI通信を伴う画面では、操作可能になるタイミングを明示します。

| 条件種別 | 記述例 | Playwrightでの実現方法 |
|---|---|---|
| 要素の表示 | ローディングスピナーが消える | `expect(spinner).toBeHidden()` |
| 要素の出現 | テーブルの行が1件以上表示される | `expect(rows).toHaveCount({ min: 1 })` |
| URL変更 | ページ遷移の完了 | `page.waitForURL('/target')` |
| APIレスポンス | 特定のAPIが返る | `page.waitForResponse('**/api/data')` |
| ネットワークアイドル | 通信がすべて完了する | `page.waitForLoadState('networkidle')` |

---

## クリーンアップ手順

テスト後に必要な後処理を記載します。`afterEach` / `afterAll` に落とし込みます。

| No. | 手順 | 方法 | タイミング |
|---|---|---|---|
| 1 | テストユーザー削除 | API (`DELETE /api/users/:id`) | afterAll |
| 2 | セッションクリア | `context.clearCookies()` | afterEach |

---

## テストケース記入例（完成形）

### TC-LOGIN-001: 正常系ログイン

**基本情報**

| 項目 | 内容 |
|---|---|
| テストケースID | `TC-LOGIN-001` |
| テスト名 | 正常系：メールアドレスとパスワードでログインできる |
| 対象ページURL | `/login` |
| 前提条件 | テストユーザーが登録済み、ログアウト状態 |
| 優先度 | High |
| タグ | `@smoke`, `@login` |

**操作ステップ**

| No. | 操作種別 | 対象ロケータ | 入力値 | 待機条件 |
|---|---|---|---|---|
| 1 | goto | — | `/login` | ページ読み込み完了 |
| 2 | fill | `data-testid="email-input"` | `test@example.com` | — |
| 3 | fill | `data-testid="password-input"` | `Password123!` | — |
| 4 | click | `data-testid="login-button"` | — | URLが `/dashboard` に変わるまで |

**検証**

| No. | 検証種別 | 対象ロケータ | 期待値 |
|---|---|---|---|
| 1 | toHaveURL | — | `/dashboard` |
| 2 | toBeVisible | `data-testid="user-menu"` | — |
| 3 | toHaveText | `data-testid="welcome-message"` | `ようこそ、テストユーザーさん` |

**クリーンアップ**

| No. | 手順 | タイミング |
|---|---|---|
| 1 | セッションクリア | afterEach |
