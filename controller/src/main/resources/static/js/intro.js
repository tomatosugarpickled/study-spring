const profileAvatar  = document.getElementById('profileAvatar');
const profileEyebrow = document.getElementById('profileEyebrow');
const profileName    = document.getElementById('profileName');
const profileHandle  = document.getElementById('profileHandle');
const introInput     = document.getElementById('introInput');
const matchList      = document.querySelector('.match-list');
const saveBtn        = document.getElementById('saveBtn');

function renderProfile(me) {
    const idStr = String(me.id).padStart(3, '0');
    profileAvatar .textContent = (me.name || '·').charAt(0);
    profileEyebrow.textContent = 'Member · No. ' + idStr;
    profileName   .textContent = me.name;
    profileHandle .textContent = '@user_' + idStr;
    introInput.value = me.intro;
}

function renderMatches(users) {
    if (!users.length) {
        matchList.innerHTML =
            '<li class="match-item"><div class="match-item__body">' +
            '<p class="match-item__intro">오늘은 추천이 없습니다.</p>' +
            '</div></li>';
        return;
    }
    matchList.innerHTML = users.map(u => `
            <li class="match-item">
                <div class="match-item__avatar">${(u.name || '·').charAt(0)}</div>
                <div class="match-item__body">
                    <p class="match-item__name">${u.name}</p>
                    <p class="match-item__intro">${u.intro}</p>
                </div>
            </li>`).join('');
}

async function fetchMe() {
    try {
        const res = await fetch('/ai/me');
        const me  = await res.json();
        console.log('me', me);
        renderProfile(me);
    } catch (err) {
        console.error(err);
    }
}

async function recommend() {
    saveBtn.disabled = true;
    const original = saveBtn.textContent;
    saveBtn.textContent = '추천 받는 중…';

    const payload = { intro: introInput.value };
    console.log('payload', payload);

    try {
        const res  = await fetch('/ai/recommend', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        const data = await res.json();
        console.log('response', data);
        renderMatches(data.users || []);
    } catch (err) {
        console.error(err);
        matchList.innerHTML =
            '<li class="match-item"><div class="match-item__body">' +
            '<p class="match-item__intro" style="color:var(--accent)">' +
            '추천을 불러오지 못했습니다.</p></div></li>';
    } finally {
        saveBtn.disabled    = false;
        saveBtn.textContent = original;
    }
}

saveBtn.addEventListener('click', recommend);

(async () => {
    await fetchMe();        // 본인 프로필 먼저 그리고
    await recommend();      // 그 자기소개로 첫 추천
})();