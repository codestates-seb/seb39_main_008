import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import TextEditor from '../components/TextEditor';
import BorderButton from '../components/common/BorderButton';
import ImageInput from '../components/common/ImageInput';
import ConfirmModal from './common/ConfirmModal';
import styled from 'styled-components';
import {
  //  addDiary,
  getBookList,
} from '../lib/api';

const WriteOrEditDiaryForm = (props) => {
  const [fileURL, setFileURL] = useState(null);
  const [booklist, setBooklist] = useState([]);
  const [confirm, setConfirm] = useState(false);

  const navigate = useNavigate();

  const {
    register,
    setValue,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm({});

  const onFileChange = (imgURL) => {
    setFileURL(imgURL);
  };

  useEffect(() => {
    register('content', {
      required: true,
      validate: (value) => {
        if (value.replace(/<[^>]*>?/g, '').trim() === '') return false;
        if (value === '<p><br></p>') return false;
      },
    });
  }, [register]);

  useEffect(() => {
    if (props.data) {
      setValue('content', props.data.content);
      setValue('title', props.data.title);
      setValue('subtitle', props.data.subtitle);
      setValue('category', props.data.category);
    }
  }, []);

  useEffect(async () => {
    const data = await getBookList(19);
    setBooklist(data.data);
  }, []);

  const onChange = (editorContent) => {
    setValue('content', editorContent);
  };

  const onUpdateDiary = (data) => {
    const updateDiaryData = {
      title: data.title,
      category: Number(data.category),
      subtitle: data.subtitle,
      content: data.content,
      image: fileURL || props.data.diaryimage,
    };
    console.log('update', updateDiaryData);
  };

  const onWriteDiary = async (data) => {
    const diaryData = {
      title: data.title,
      content: data.content,
      category: Number(data.category),
      image: fileURL,
    };
    console.log(diaryData);
    // const res = await addDiary(diaryData);
    // console.log(res);
    console.log('write', diaryData);
  };

  const editorContent = watch('content');

  const onCancle = () => {
    setConfirm(!confirm);
  };

  const confirmCancle = (res) => {
    if (res) {
      onCancle();
      navigate(-1);
    } else {
      onCancle();
    }
  };

  return (
    <Container
      onSubmit={
        props.isEdit ? handleSubmit(onUpdateDiary) : handleSubmit(onWriteDiary)
      }
    >
      <Mid>
        <div>
          <input
            // wrap="on"
            className="title"
            type="text"
            placeholder="제목"
            {...register('title', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />

          <input
            // wrap="on"
            className="subtitle"
            id="subtitle"
            type="text"
            placeholder="소제목"
            {...register('subtitle', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />

          <br />
          <label className="book" htmlFor="book">
            일기장
          </label>
          {props.isEdit ? (
            <span className="booktitle">{props.booktitle || '나의하루'}</span>
          ) : (
            <select id="book" {...register('book')}>
              {booklist &&
                booklist.map((el, idx) => (
                  <option value={el.id} key={idx}>
                    {el.title}
                  </option>
                ))}
            </select>
          )}
          <br />
          <label className="category" htmlFor="category">
            주제
          </label>
          <select id="category" {...register('category')}>
            {['일상 공유', '공감과 치유', '문화 생활', '여행 기록', '자유'].map(
              (el, idx) => (
                <option value={idx} key={idx}>
                  {el}
                </option>
              )
            )}
          </select>
        </div>
        <ImageInput onFileChange={onFileChange} />
      </Mid>
      <Bottom>
        {props.isEdit && (
          <img
            className="previewImg"
            src={fileURL || props.data.diaryimage}
            alt="preview Img"
          />
        )}
        <TextEditor content={editorContent} onChange={onChange} />
        <div className="rightbox">
          <p className="Error">{errors && '모든 내용을 입력해 주세요'}</p>
          <BorderButton
            width="120px"
            height="40px"
            type="submit"
            fontSize={`var(--fontSizeM)`}
            text={props.isEdit ? '수정하기' : '기록하기'}
          />
          <BorderButton
            text="취소"
            width="120px"
            height="40px"
            fontSize={`var(--fontSizeM)`}
            onClick={() => {
              setConfirm(!confirm);
            }}
          />
        </div>
      </Bottom>
      {confirm && (
        <ConfirmModal
          message={'취소하시겠습니까?'}
          onComfirm={confirmCancle}
          target={'작성한 내용은 복구할 수 없습니다'}
        />
      )}
    </Container>
  );
};

export default WriteOrEditDiaryForm;

export const Container = styled.form`
  margin: 0 auto;
  width: 100%;
`;

export const Mid = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 0 var(--spaceS);
  border-bottom: 1px solid ${({ theme }) => theme.colors.border};

  & > div {
    width: 50%;
  }

  & input {
    padding: 0;
    margin-top: var(--spaceM);
    overflow: visible;
    width: 100%;
    height: 100%;
    border: none;
    display: inline-block;
    background-color: transparent;
    color: ${({ theme }) => theme.colors.text1};
  }

  & select {
    width: 30%;
    border: none;
    background-color: ${({ theme }) => theme.colors.dimGrey};
    border-radius: var(--borderRadiusS);
    color: ${({ theme }) => theme.colors.text1};
  }

  & input:focus-visible {
    outline: none;
  }

  .title {
    height: 50px;
    font-size: var(--fontSizeLL);
  }

  .title::placeholder {
    font-size: var(--fontSizeLL);
    color: ${({ theme }) => theme.colors.text2};
  }

  .subtitle {
    height: 50px;
    font-size: var(--fontSizeL);
  }

  .subtitle::placeholder {
    font-size: var(--fontSizeL);
    color: ${({ theme }) => theme.colors.text2};
  }

  label {
    margin-top: var(--spaceM);
    width: 50px;
    display: inline-block;
    color: ${({ theme }) => theme.colors.text2};
  }

  .booktitle {
    font-size: var(--fontSizeM);
  }

  .radioBox {
    margin-top: var(--spaceM);
    font-size: var(--fontSizeS);

    p {
      color: ${({ theme }) => theme.colors.text1};
      margin-bottom: var(--spaceS);
    }

    input,
    label {
      cursor: pointer;
      margin: calc(var(--spaceS) / 3);
      display: inline;
    }

    input {
      width: 10px;
      display: inline;
    }
  }

  .addImgButton {
    cursor: pointer;
    margin: var(--spaceM) 0 var(--spaceM) var(--spaceS);
    width: 50%;
    height: 427.5px;
    overflow: hidden;
    border-radius: var(--borderRadiusM);
    border: 1px dashed ${({ theme }) => theme.colors.border};
    display: flex;
    align-items: center;
    justify-content: center;

    & > p {
      font-size: var(--fontSizeL);
      color: ${({ theme }) => theme.colors.text2};
    }

    &:hover {
      & > p {
        color: ${({ theme }) => theme.colors.text1};
      }
    }
  }

  .image {
    display: none;
  }

  @media screen and (max-width: 576px) {
    align-items: center;
    flex-direction: column;
    & > div {
      width: 100%;
    }

    .addImgButton {
      margin: var(--spaceM) 0;
      width: 100%;
    }
  }
`;

export const Bottom = styled.div`
  width: 100%;
  padding-bottom: var(--spaceL);

  .preview {
    p {
      font-size: var(--fontSizeM);
      padding: var(--spaceS) var(--spaceS) 0 var(--spaceS);
    }
  }

  .previewImg {
    padding: var(--spaceS);
    width: 100%;
  }

  .quill {
    * {
      color: ${({ theme }) => theme.colors.text1};
    }
    border-top: 1px solid ${({ theme }) => theme.colors.border};
    z-index: 2000;
    padding: var(--spaceM) var(--spaceS);
    color: ${({ theme }) => theme.colors.text2};
    min-height: 20rem;
  }

  .ql-editor {
    font-size: var(--fontSizeM);
    padding: 0px;
  }

  .rightbox {
    & > * {
      margin-right: var(--spaceS);
    }

    & > button:last-child {
      margin: 0;
    }
    display: flex;
    justify-content: end;
    align-items: flex-end;
    margin: var(--spaceM) 0;
  }
  .ql-tooltip-arrow {
    border-bottom: transparent !important;
  }
  .ql-tooltip {
    left: 0px !important;
  }
  .Error {
    font-size: calc(var(--fontSizeS) * 1.2);
    color: ${({ theme }) => theme.colors.red};
    display: inline-block;
  }
`;
