<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>職業情報</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>

    <div class="main-container">

        <!-- 左カラム -->
        <div class="left-col">
            <p class="page-title">職業情報</p>
            <p class="page-desc">職業に関する情報を入力してください。</p>
        </div>

        <!-- 右カラム -->
        <div class="right-col">

            <form action="<c:url value='/userInfo002'/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <!-- 職業 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">職業</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <div class="radio-group">
                            <label class="radio-label">
                                <input type="radio" name="occupation" value="employee" onchange="toggleOtherInput(this)" />
                                <span class="radio-text">会社員</span>
                            </label>
                            <label class="radio-label">
                                <input type="radio" name="occupation" value="selfEmployed" onchange="toggleOtherInput(this)" />
                                <span class="radio-text">自営業</span>
                            </label>
                            <label class="radio-label">
                                <input type="radio" name="occupation" value="student" onchange="toggleOtherInput(this)" />
                                <span class="radio-text">学生</span>
                            </label>
                            <label class="radio-label">
                                <input type="radio" name="occupation" value="other" onchange="toggleOtherInput(this)" />
                                <span class="radio-text">その他</span>
                            </label>
                        </div>
                        <!-- その他選択時のみ表示 -->
                        <div id="otherInputWrap" class="other-input-wrap">
                            <input type="text" name="occupationOther" placeholder="職業を入力してください" />
                        </div>
                    </div>
                </div>

                <!-- 勤務先 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">勤務先</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="workplace" placeholder="例）株式会社〇〇" />
                    </div>
                </div>

                <!-- 年収 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">年収</span>
                    </div>
                    <div class="field-input-wrap">
                        <select name="annualIncome" class="field-select">
                            <option value="">選択してください</option>
                            <option value="under2m">200万円未満</option>
                            <option value="2m_4m">200万円〜400万円未満</option>
                            <option value="4m_6m">400万円〜600万円未満</option>
                            <option value="6m_8m">600万円〜800万円未満</option>
                            <option value="8m_10m">800万円〜1000万円未満</option>
                            <option value="over10m">1000万円以上</option>
                        </select>
                    </div>
                </div>

                <!-- 家族構成 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">家族構成</span>
                    </div>
                    <div class="field-input-wrap">
                        <div class="checkbox-group">
                            <label class="checkbox-label">
                                <input type="checkbox" name="familyStructure" value="single" />
                                <span class="checkbox-text">単身</span>
                            </label>
                            <label class="checkbox-label">
                                <input type="checkbox" name="familyStructure" value="coupleOnly" />
                                <span class="checkbox-text">夫婦のみ</span>
                            </label>
                            <label class="checkbox-label">
                                <input type="checkbox" name="familyStructure" value="coupleWithChild" />
                                <span class="checkbox-text">夫婦＋子</span>
                            </label>
                            <label class="checkbox-label">
                                <input type="checkbox" name="familyStructure" value="singleParent" />
                                <span class="checkbox-text">一人親＋子</span>
                            </label>
                            <label class="checkbox-label">
                                <input type="checkbox" name="familyStructure" value="withParents" />
                                <span class="checkbox-text">親と同居</span>
                            </label>
                            <label class="checkbox-label">
                                <input type="checkbox" name="familyStructure" value="other" />
                                <span class="checkbox-text">その他</span>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="submit-wrap">
                    <button type="button" class="temp-save-btn">一時保存</button>
                    <button type="submit" class="submit-btn">次へ</button>
                </div>

            </form>

        </div>
    </div>

    <script>
        function toggleOtherInput(radio) {
            const otherInputWrap = document.getElementById('otherInputWrap');
            otherInputWrap.style.display = radio.value === 'other' ? 'block' : 'none';
        }
    </script>

</body>
</html>
